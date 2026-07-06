package assignment;

import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

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
            if(dataId[index].contains(target)){
                returnData.add(lines);
                break;
            }
        }
        return returnData;
    }
    
    public static String filterID(String id, String fileName){
        for(String line : readFile(fileName)){
            String[] p = line.split(",");
            if(p[0].equals(id)){
                return line;
            }
        }
        return null;
    }
    
    public static Date toDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate;
        try {
            tempDate = sdf.parse(date);
        } catch (ParseException ex) {
            tempDate = null;
        }
        return tempDate;
    }
    
//    public static void removeData()
}
