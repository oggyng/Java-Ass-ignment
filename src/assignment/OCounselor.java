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
    private boolean Mon, Tue, Wed, Thu, Fri;
    private String specialise;
    public OCounselor(String name, String gender, Date DoB, String email){
        super(name, gender, DoB, email);
        this.Mon = true;
        this.Tue = true;
        this.Wed = true;
        this.Thu = true;
        this.Fri = true;
        this.specialise = "None";
    }
    public OCounselor(String id, String name, String gender, Date DoB, String email){
        super(id,name,gender,DoB,email);
        this.Mon = true;
        this.Tue = true;
        this.Wed = true;
        this.Thu = true;
        this.Fri = true;
        this.specialise = "None";
    }
    
    public OCounselor(String id, String name, String gender, Date DoB, String email,Boolean[] available, String specialise){
        super(id,name,gender,DoB,email);
        this.Mon = available[0];
        this.Tue = available[1];
        this.Wed = available[2];
        this.Thu = available[3];
        this.Fri = available[4];
        this.specialise = specialise;
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
    
    //roster stuff
    public Boolean[] getAvailable(){
        Boolean[] available = {Mon,Tue,Wed,Thu,Fri};
        return available;
    }
    public String getSpecialise(){ return specialise;}
    
    
    public void setAvailable(Boolean[] available){
        this.Mon = available[0];
        this.Tue = available[1];
        this.Wed = available[2];
        this.Thu = available[3];
        this.Fri = available[4];
    }
    
    public void setSpecialise(String specialise){
        this.specialise = specialise;
    }
    
    public void initProfile(){
        String data =  this.getId() + "," + this.Mon + "," + this.Tue + "," + this.Wed + "," + this.Thu + "," + this.Fri + "," + this.specialise;
        Functions.inputFile("loginData.txt",data,"append");
    }
}
