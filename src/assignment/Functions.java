package assignment;

import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Functions {
    public static ArrayList<String> readFile(String fileName) throws IOException{
        File f = new File(fileName);
        if(!f.exists()){
            System.out.println("Debug: File not found btw");
        }
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        ArrayList<String> readData = new ArrayList<>();
        
        while((line = br.readLine())!=null){
            readData.add(line);
        }
        
        br.close();
        fr.close();
        
        return readData;
    }
    
    public static void inputFile(String fileName, ArrayList<String> inputData, String mode) throws IOException{
        Boolean inputMode; 
        
        switch(mode){
            case("append") -> inputMode = true;
            case("write") -> inputMode = false;
            default -> {inputMode = true; System.out.println("Debug: No mode assigned, automatically changed to append mode");}
        }
        
        File f = new File(fileName);
        if(!f.exists()){
            System.out.println("Debug: File not found btw, a new file will be create");
        }
        FileWriter fw = new FileWriter(f,inputMode);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for(int i=0;i<inputData.size();i++){
            bw.write(inputData.get(i));
        }
        
        bw.close();
        fw.close();
        
        System.out.println("Debug: File Updated Successfully!");
    }
    
//    public static void removeData()
}
