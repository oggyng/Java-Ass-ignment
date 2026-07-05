/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

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
    
    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static counselorObject extractFile(String line){
        String[] p = line.split(",");
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        Date tempDate = sdf.parse(p[3]);
        return new counselorObject(p[0],p[1],p[2],tempDate,p[4]);
    }
    
}
