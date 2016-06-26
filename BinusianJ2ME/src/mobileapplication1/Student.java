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
public class Student extends Binusian{

    int graduationYear;
    
    public Student(){
        
    }
    
    public Student(String name, String id, String phone, int graduationYear){
        super(name, id, phone);
        this.graduationYear = graduationYear;
    }
    
    public String getId() {
        return Integer.toString(graduationYear%100) + id;
    }
    
}
