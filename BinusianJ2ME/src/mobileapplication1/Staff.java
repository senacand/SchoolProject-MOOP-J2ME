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
public class Staff extends Binusian {
    String position;
    
    public Staff(){
        
    }
    
    public Staff(String name, String id, String phone, String position){
        super(name, id, phone);
        this.position = position;
    }

    public String getId() {
        return "S" + id + position.charAt(0);
    }
}
