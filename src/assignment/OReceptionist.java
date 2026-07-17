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
public class OReceptionist extends ORoleParent {
    OReceptionist(String name, String gender, Date DoB, String email){
        super(name, gender, DoB, email);
    }
    OReceptionist(String id, String name, String gender, Date DoB, String email){
        super(id, name, gender, DoB, email);
    }
    public static OReceptionist extractFile(String line){
        String[] p = line.split(",");
        return new OReceptionist(p[0],p[1],p[2],Functions.StringtoDate(p[3]),p[4]);
    }
    
    public static ArrayList<OReceptionist> extractAll(){
        ArrayList<OReceptionist> aos = new ArrayList<>();
        for(String line : Functions.filterData(Functions.readFile("userData.txt"), "R", 0)){
            aos.add(extractFile(line));
        }
        return aos;
    }
    
}
