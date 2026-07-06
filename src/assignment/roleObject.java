/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author User
 */
public abstract class roleObject {
    private String id, name, gender, email;
    private Date DoB;
    
    public roleObject(String id, String name, String gender, Date DoB, String email){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.DoB = DoB;
        this.email = email;
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getGender(){return gender;}
    public Date getDoB(){return DoB;}
    public String getEmail(){return email;}
    public int getAge(){
        Calendar dob = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        dob.setTime(DoB);
        return now.get(Calendar.YEAR)-dob.get(Calendar.YEAR);
    }

    public void setId(String id, String role){
        String c;
        switch(role){
            case "Admin" -> c = "A";
            case "Receptionist" -> c= "R";
            case "Counselor" -> c= "C";
            case "Student" -> c="S";
            default -> c=null;
        }
        ArrayList<String> lines = Functions.filterData(Functions.readFile("userData.txt"),c,0);
        int max = 0;
        for(String line : lines){
            String[] p = line.split(",");
            int idNum = Integer.parseInt(p[0].substring(1));
            if(this.name.equals(p[1])){
                System.out.println("Debug: User exists!");
                return;
            }
            if(idNum > max){
                max = idNum;
            }
        }
        this.id = c+String.format("%03d", max+1);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public boolean setGender(String gender){
        if(gender.equals("Male")||gender.equals("Female")){
            this.gender = gender;
            return true;
        }
        else{
            this.gender = null;
            return false;
        }
    }
    public boolean setDoB(String DoB){
        this.DoB = Functions.toDate(DoB);
        return true;
    }
    public boolean setEmail(String email){
        if(email.matches("[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}")){
            this.email = email;
            return true;
        }
        else{
            this.email = null;
            return false;
        }
    }
}



