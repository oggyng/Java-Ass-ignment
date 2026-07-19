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
                bw.newLine();
            }
            bw.close();
        }
        catch(IOException e){
            System.out.println("Sum Ting Wong!");
        }
        
        System.out.println("Debug: File Updated Successfully!");
    }
    
    public static void inputFile(String fileName, String inputData, String mode){
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
            bw.write(inputData);
            bw.newLine();
            bw.close();
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
        Date date = StringtoDate(DateTimetoString(dateTime).split(" ")[0]);
        return date;
    }
    
    public static void removeData(String id, String fileName){
        ArrayList<String> tempList = new ArrayList<>();
        for(String lines : readFile(fileName)){
            if(id.equals(lines.split(",")[0])){
                continue;
            }
            tempList.add(lines);
        }
        inputFile(fileName,tempList,"write");
    }
    
    public static ArrayList<String> checkDayClash(Date date, String counselorID){
        final String[] time = {"10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00"};
        ArrayList<String> timeSlot = new ArrayList<>();
        timeSlot.addAll(Arrays.asList(time));
        String targetDate  = DatetoString(date);
        for(String lines : readFile("appointment.txt")){
            String[] p = lines.split(",");
            String appointDate = p[3].split(" ")[0];
            
            if(!targetDate.equals(appointDate)){
                continue;
            }
            if(p[7].equals("Done") || p[7].equals("Cancelled")){
                continue;
            }
            if(!p[1].equals(counselorID)){
                continue;
            }
            for(String t : time){
                String slotStr  = DatetoString(date) + " " + t;
                String startStr = p[3];
                if(slotStr.equals(startStr)){
                    timeSlot.remove(t);
                    break;
                }
            }
        }
        return timeSlot;
    }
    

    
    public static ArrayList<String> checkDayClash(Date date){
        final String[] time = {"10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00"};
        ArrayList<String> timeSlot = new ArrayList<>();
        timeSlot.addAll(Arrays.asList(time));
        for(String lines : readFile("appointment.txt")){
            String[] p = lines.split(",");
            if(!date.equals(StringtoDate(p[3].split(" ")[0]))){
                continue;
            }
            if(!p[5].equals("Pending") && !p[5].equals("Confirmed")){
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
    
    public static ArrayList<Integer> ageRange(){
        Calendar c = Calendar.getInstance();
        ArrayList<Integer> range = new ArrayList<>();
        int year = c.get(Calendar.YEAR);
        int temp = year;
        while(year > temp-80){
            range.add(year);
            year--;
        }
        return range;
    }
    
    public static int checkDate(int year, String month){
        int day;
        if(year%4==0){
            day = switch(month){
                case "Jan","Mar","May","Jul","Aug","Oct","Dec" -> 31;
                case "Apr","Jun","Sep","Nov" -> 30;
                case "Feb" -> 29;   
                default -> 31;
            };
        }
        else{
            day = switch(month){
                case "Jan","Mar","May","Jul","Aug","Oct","Dec" -> 31;
                case "Apr","Jun","Sep","Nov" -> 30;
                case "Feb" -> 28;   
                default -> 31;
            };
        }
        return day;
    }
    
    public static String[] DoBtoList(String dob){
        String[] date = dob.split("-");
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        String month = months[Integer.parseInt(date[1])-1];
        String[] rDate = {date[0],month,date[2]};
        return rDate;
    }
    
    public static String ListtoDoB(String[] dob){
        String month = switch(dob[1]){
            case "Jan" -> "01";
            case "Feb" -> "02";
            case "Mar" -> "03";
            case "Apr" -> "04";
            case "May" -> "05";
            case "Jun" -> "06";
            case "Jul" -> "07";
            case "Aug" -> "08";
            case "Sep" -> "09";
            case "Oct" -> "10";
            case "Nov" -> "11";
            case "Dec" -> "12";
            default -> null;
        };
        String rDate = dob[0]+"-"+month+"-"+dob[2];
        return rDate;
    }
    
    public static void updateAppointFile(){
        Calendar now = Calendar.getInstance();
        for(String line : readFile("appointment.txt")){
            String[] p = line.split(",");
            if(now.after(StringtoDateTime(p[4]))&&p[7].equals("Confirmed")){
                p[6] = "0";
            } 
        }
    }
}
