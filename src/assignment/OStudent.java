/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author User
 */
public class OStudent extends ORoleParent{
    OStudent(String name, String gender, Date DoB, String email){
        super(name, gender, DoB, email);
    }
    OStudent(String id, String name, String gender, Date DoB, String email){
        super(id, name, gender, DoB, email);
    }
    public static OStudent extractFile(String line){
        String[] p = line.split(",");
        return new OStudent(p[0],p[1],p[2],Functions.StringtoDate(p[3]),p[4]);
    }
    
    public static ArrayList<OStudent> extractAll(){
        ArrayList<OStudent> aos = new ArrayList<>();
        for(String line : Functions.filterData(Functions.readFile("userData.txt"), "S", 0)){
            aos.add(extractFile(line));
        }
        return aos;
    }
    
}
