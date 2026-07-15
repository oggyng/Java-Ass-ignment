/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class OCounselor extends ORoleParent {
    public OCounselor(String id, String name, String gender, Date DoB, String email){
        super(id,name,gender,DoB,email);
    }
   
    
    public static OCounselor extractFile(String line){
        String[] p = line.split(",");
        return new OCounselor(p[0],p[1],p[2],Functions.StringtoDate(p[3]),p[4]);
    }
    
    public static ArrayList<OCounselor> extractAll(){
        ArrayList<OCounselor> cos = new ArrayList<>();
        for(String line : Functions.filterData(Functions.readFile("userData.txt"), "C", 0)){
            cos.add(extractFile(line));
        }
        return cos;
    }
}
