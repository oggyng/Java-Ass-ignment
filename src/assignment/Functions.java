package assignment;

import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Arrays;

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
            if(index!=-1){
                if(dataId[index].contains(target)){
                    returnData.add(lines);
                }
            }
            else{
                for(String p : dataId){
                    if(p.contains(target)){
                        returnData.add(lines);
                        break;
                    }
                }
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
    
    public static Date StringtoDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate;
        try {
            tempDate = sdf.parse(date);
        } catch (ParseException ex) {
            tempDate = null;
        }
        return tempDate;
    }
    
    public static Calendar StringtoDateTime(String dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar tempDate = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateTime);
            tempDate.setTime(date);
        } catch (ParseException ex) {
            tempDate = null;
        }
        return tempDate;
    }
    
    public static String DateTimetoString(Calendar dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sdf.format(dateTime.getTime());
        return date;
    }
    
    public static String DatetoString(Date dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateTime);
        return date;
    }
    
    public static Calendar DatetoDateTime(Date date, String time){
        String tempDate = DatetoString(date)+" "+time;
        Calendar tempDateTime = StringtoDateTime(tempDate);
        return tempDateTime;
    }
    
    public static Date DateTimetoDate(Calendar dateTime){
        Date date = StringtoDate(DateTimetoString(dateTime).substring(0,9));
        return date;
    }
    
    public static void removeData(String id, String fileName){
        ArrayList<String> tempList = new ArrayList<>();
        for(String lines : readFile(fileName)){
            String[] p = lines.split(",");
            if(id.equals(p[0])){
                continue;
            }
            tempList.add(lines);
        }
        inputFile(fileName,tempList,"write");
    }
    
    public static ArrayList<String> checkDayClash(Date date){
        final String[] time = {"10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};
        ArrayList<String> timeSlot = new ArrayList<>();
        timeSlot.addAll(Arrays.asList(time));
        for(String lines : readFile("appointment.txt")){
            String[] p = lines.split(",");
            if(!date.equals(DateTimetoDate(StringtoDateTime(p[3])))){
                continue;
            }
            if(!p[5].equals("Approved") || !p[5].equals("PendingAdd") || !p[5].equals("PendingRemove") || !p[5].equals("PendingUpdate")){
                continue;
            }
            Calendar tempStart = StringtoDateTime(p[3]); 
            for(String t : time){
                if(DatetoDateTime(date,t).equals(tempStart)){
                    timeSlot.remove(t);
                    break;
                }
            }
        }
        return timeSlot;
    }
}
