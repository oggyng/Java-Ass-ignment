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
public class roleObject {
    final private String userData = "userData.txt";
    public static class Counselor{
        private String id, name, gender, email;
        private Date DoB;
        Counselor(String id, String name, String gender, Date DoB, String email){
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
        
        public void setId(String id){
            if(!id.matches("C\\d+")){
                // do something change status or what
            }
            else{
                this.id = id;
            }
        }
        public String setName(){return name;}
        public String setGender(){return gender;}
        public Date setDoB(){return DoB;}
        public String setEmail(){return email;}
    }
}
