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
public abstract class Binusian {
    String name, id, phone;
    
    public Binusian(){
        
    }
    
    public Binusian(String name, String id, String phone){
        this.name = name;
        this.id = id;
        this.phone = phone;
    }
    
    public abstract String getId();
}
