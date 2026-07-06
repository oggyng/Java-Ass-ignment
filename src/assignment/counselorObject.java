/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author User
 */
public class counselorObject extends roleObject {
    private String role;
    public counselorObject(String role, String id){
        super(id);
        this.role = role;
    }
    public counselorObject(String id, String name, String gender, Date DoB, String email){
        super(id,name,gender,DoB,email);
    }
   
    
    public static counselorObject extractFile(String line){
        String[] p = line.split(",");
        return new counselorObject(p[0],p[1],p[2],Functions.toDate(p[3]),p[4]);
    }
}
