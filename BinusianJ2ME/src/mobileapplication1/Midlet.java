/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplication1;

import java.util.Vector;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Sena
 */
public class Midlet extends MIDlet implements CommandListener{

    Display mDisplay;
    String[] mainMenu = {"View Data", "New Data"};
    String[] types = {"Student", "Lecturer", "Staff"};
    Command cmdOk = new Command("Ok", Command.OK, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    
    List lMainMenu = new List("Binusian Data", List.IMPLICIT, mainMenu, null);
    List lData = null;
    Form fAdd = new Form("Add Data");
    
    List lAddMenu = new List("What do you want to add?", List.IMPLICIT, new String[]{"Student", "Lecturer", "Staff"}, null);
    
    TextField fAddName = new TextField("Name", "", 100, TextField.ANY);
    TextField fAddID = new TextField("ID", "", 100, TextField.NUMERIC);
    TextField fAddPhone = new TextField("Phone Number", "", 100, TextField.PHONENUMBER);
    TextField fAddGrad = new TextField ("Graduation Year", "", 4, TextField.NUMERIC);
    TextField fAddStartYear = new TextField("Start Year", "", 4, TextField.NUMERIC);
    TextField fAddPosition = new TextField("Position", "", 100, TextField.ANY);
    TextField fAddType;
    
    Vector vB = new Vector();
    
    Form detailData = new Form("Data");
    
    public void startApp() {
        lMainMenu.addCommand(cmdOk);
        lMainMenu.setCommandListener(this);
        mDisplay = Display.getDisplay(this);
        mDisplay.setCurrent(lMainMenu);
        
        lAddMenu.addCommand(cmdOk);
        lAddMenu.addCommand(cmdBack);
        lAddMenu.setCommandListener(this);
    }
    
    public void pauseApp() {
    
    }
    
    private Form readData(int id){
        detailData.deleteAll();
        Binusian data = (Binusian)vB.elementAt(id);
        detailData.setTitle(data.getId());
        detailData.append(new StringItem("Name: ", data.name));
        detailData.append(new StringItem("Phone Number: ", data.phone));
        if(data instanceof Student){
            detailData.append(new StringItem("Est. Graduation: ", Integer.toString(((Student) data).graduationYear)));
        }
        else if(data instanceof Lecturer){
            detailData.append(new StringItem("Entrance Year: ", Integer.toString(((Lecturer)data).startYear)));
        }
        else{
            detailData.append(new StringItem("Position: ", ((Staff)data).position));
        }
        
        detailData.addCommand(cmdBack);
        detailData.setCommandListener(this);
        
        return detailData;
    }
    
    public Form dataAdd(int type){
        fAdd.deleteAll();
        fAdd.append(fAddName);
        fAdd.append(fAddID);
        fAdd.append(fAddPhone);
        ChoiceGroup test = new ChoiceGroup("test", ChoiceGroup.IMPLICIT);
        
        switch(type){
            case 0:
                fAdd.append(fAddGrad);
                break;
            case 1:
                fAdd.append(fAddStartYear);
                break;
            case 2:
                fAdd.append(fAddPosition);
                break;
        }
        
        fAdd.addCommand(cmdOk);
        fAdd.addCommand(cmdBack);
        fAdd.setCommandListener(this);
        return fAdd;
    }
    
    public List dataMenu(boolean refresh){
        if(lData==null || refresh){
            lData = new List("Binusian Data", List.IMPLICIT);
            for(int i=0; i<vB.size(); i++){
                Binusian bData = (Binusian)vB.elementAt(i);
                if(bData instanceof Student){
                    lData.append(bData.name + " (Student)", null);
                }
                else if(bData instanceof Lecturer){
                    lData.append(bData.name + " (Lecturer)", null);
                }
                else{
                    lData.append(bData.name + " (Staff)", null);
                }
            }
            lData.addCommand(cmdOk);
            lData.addCommand(cmdBack);
            lData.setCommandListener(this);
        }
        return lData;
    }
    
    public void destroyApp(boolean unconditional) {
        
    }

    public void commandAction(Command c, Displayable d) {
        if(d == lMainMenu){
            if(c==List.SELECT_COMMAND || c==cmdOk){
                if(lMainMenu.getSelectedIndex()==0){
                    mDisplay.setCurrent(dataMenu(true));
                }
                else{
                    mDisplay.setCurrent(lAddMenu);
                }
            }
        }
        else if(d == lData){
            if(c==cmdBack){
                mDisplay.setCurrent(lMainMenu);
            }
            else{
                mDisplay.setCurrent(readData(lData.getSelectedIndex()));
            }
        }
        else if(d == lAddMenu){
            if(c==cmdBack){
                mDisplay.setCurrent(lMainMenu);
            }
            else{
                mDisplay.setCurrent(dataAdd(lAddMenu.getSelectedIndex()));
            }
        }
        else if(d == fAdd){
            if(c==cmdOk){
                String name = fAddName.getString();
                String id = fAddID.getString();
                String phone = fAddPhone.getString();
                if(fAdd.get(3) == fAddGrad){
                    vB.addElement(new Student(name, id, phone, Integer.parseInt(fAddGrad.getString())));
                }
                else if(fAdd.get(3) == fAddStartYear){
                    vB.addElement(new Lecturer(name, id, phone, Integer.parseInt(fAddStartYear.getString())));
                }
                else{
                    vB.addElement(new Staff(name, id, phone, fAddPosition.getString()));
                }
                
                fAddName.setString("");
                fAddID.setString("");
                fAddPhone.setString("");
                fAddGrad.setString("");
                fAddStartYear.setString("");
                fAddPosition.setString("");
                
                Alert alert = new Alert("Success", "Data has been added", null, AlertType.INFO);
                alert.setTimeout(3000);
                mDisplay.setCurrent(alert, dataMenu(true));
            }
            else if(c==cmdBack){
                mDisplay.setCurrent(lAddMenu);
            }
        }
        else if(d==detailData){
            if(c==cmdBack){
                mDisplay.setCurrent(lData);
            }
        }
    }
}
