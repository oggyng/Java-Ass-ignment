/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.Date;
/**
 *
 * @author User
 */
public abstract class roleObject {
    private String id, name, gender, email;
    private Date DoB;
    roleObject(String id, String name, String gender, Date DoB, String email){
        this.id = id;
        this.name = name;
        this. gender = gender;
        this.DoB = DoB;
        this.email = email;
    }

    public String getId(){return id;}
    public String getName(){return name;}
    public String getGender(){return gender;}
    public Date getDoB(){return DoB;}
    public String getEmail(){return email;}

    public abstract void setId(String id);
    public void setName(String name){
        this.name = name;
    }
    public boolean setGender(String gender, String status){
        if(gender.equals("Male")||gender.equals("Female")){
            this.gender = gender;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean setDoB(String status){
        return DoB;
    }
    public String setEmail(String status){
        return email;
    }
}

