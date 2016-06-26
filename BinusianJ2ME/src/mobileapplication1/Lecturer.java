/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplication1;

/**
 *
 * @author Sena
 */
public class Lecturer extends Binusian{

    int startYear;
    
    public Lecturer(){
        
    }
    
    public Lecturer(String name, String id, String phone, int startYear){
        super(name, id, phone);
        this.startYear = startYear;
    }
    
    public String getId() {
        return "D" + id;
    }
    
}
