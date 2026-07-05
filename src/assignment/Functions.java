package assignment;

import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Functions {
    public static ArrayList<String> readFile(String fileName){
        File f = new File(fileName);
        if(!f.exists()){
            System.out.println("Debug: File not found btw");
        }
        ArrayList<String> readData = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String line = null;

            while((line = br.readLine())!=null){
                readData.add(line);
            }

            br.close();            
        }
        catch(IOException e){
            System.out.println("Sum Ting Wong");
        }
        return readData;
        
        
    }
    
    public static void inputFile(String fileName, ArrayList<String> inputData, String mode){
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

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(f,inputMode))){
            for(int i=0;i<inputData.size();i++){
                bw.write(inputData.get(i));
            bw.close();
            }
        }
        catch(IOException e){
            System.out.println("Sum Ting Wong!");
        }
        
        System.out.println("Debug: File Updated Successfully!");
    }
    
    public static ArrayList<String> filterData(ArrayList<String> data, String target, int index){
        ArrayList<String> returnData = new ArrayList<>();
        for(String lines : data){
            String[] dataId = lines.split(",");
            if(target.equals(dataId[index])){
                returnData.add(lines);
                break;
            }
        }
        return returnData;
    }
    
//    public static void removeData()
}
