package assignment;

import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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
    public static String filterNote(String id, String fileName){
        for(String line : readFile(fileName)){
            String[] p = line.split("\\|");
            if(p[0].equals(id)){
                return line;
            }
        }
        return null;
    }
    
    public static String filterName(String name, String fileName){
        for(String line : readFile(fileName)){
            String[] p = line.split(",");
            if(p[1].equals(name)){
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
        Calendar today = Calendar.getInstance();
        ArrayList<String> timeSlot = new ArrayList<>();
        timeSlot.addAll(Arrays.asList(time));
        String targetDate  = DatetoString(date);
        Calendar temp = Calendar.getInstance();
        temp.setTime(date);
        
        String[] line = filterData(readFile("cProfile.txt"),counselorID, 0).get(0).split(",");
        ArrayList<Boolean> available = new ArrayList<>();
        for(int i=1;i<6;i++){
            available.add(Boolean.valueOf(line[i]));
        }
        if(!available.get(temp.get(Calendar.DAY_OF_WEEK)-2)){
            return new ArrayList<String>();
        }
        
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
                if(slotStr.equals(startStr)||today.after(StringtoDateTime(slotStr))){
                    timeSlot.remove(t);
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
        ArrayList<String> tempData = new ArrayList<>();
        for(String line : readFile("appointment.txt")){
            if(line.isEmpty()){
                continue;
            }
            String[] p = line.split(",");
            if(now.after(StringtoDateTime(p[4]))&&p[7].equals("Confirmed")){
                p[6] = "0";
                p[7] = "Done";
            } 
            tempData.add(String.join(",", p));
        }
        inputFile("appointment.txt",tempData,"write");
    }
    
    public static String getTargetDay(Calendar date, String time){
        int hour = Integer.parseInt(time.split(":")[0]);
        date.set(Calendar.HOUR_OF_DAY,hour);
        String target = DateTimetoString(date);
        return target;
    }
    
    public static ArrayList<String> appointmentInNotes(){
        ArrayList<String> data = new ArrayList<>();
        for(String lines:readFile("note.txt")){
            String[] p = lines.split("|");
            data.add(p[0]);
        }
        return data;
    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
    // The following functions are used for generating test case data, for lecturer to generate accurate data for assignment.txt and cProfile.txt
    // simply call generateTestData() will overwrite every data in both file to sync with the current date
    // THIS IS NOT RELATED TO ASSIGNMENT REQUIREMENTS, ONLY FOR ACCURATE TEST CASE PURPOSES
    
    public static void generateTestData(){
        final String[] HOURS = {"10","11","12","13","14","15","16","17","18","19"};
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        ArrayList<String>   cId   = new ArrayList<>();
        ArrayList<String>   cSpec = new ArrayList<>();
        ArrayList<boolean[]> cDay = new ArrayList<>();
        for(String line : readFile("cProfile.txt")){
            if(line.trim().isEmpty()) continue;
            String[] p = line.split(",");
            if(p.length < 7) continue;
            cId.add(p[0]);
            cSpec.add(p[6]);
            cDay.add(new boolean[]{ Boolean.valueOf(p[1]), Boolean.valueOf(p[2]),
                                    Boolean.valueOf(p[3]), Boolean.valueOf(p[4]),
                                    Boolean.valueOf(p[5]) });
        }

        ArrayList<String> sId = new ArrayList<>();
        for(String line : readFile("userData.txt")){
            if(line.trim().isEmpty()) continue;
            String[] p = line.split(",");
            if(p.length >= 2 && p[0].startsWith("S")) sId.add(p[0]);
        }
        if(cId.isEmpty() || sId.isEmpty()){
            System.out.println("Debug: cProfile.txt or userData.txt missing - aborted.");
            return;
        }

        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<String> usedC = new ArrayList<>();
        ArrayList<String> usedS = new ArrayList<>();

        Calendar now = Calendar.getInstance();
        int nowHour  = now.get(Calendar.HOUR_OF_DAY);

        ArrayList<Calendar> past = new ArrayList<>();
        Calendar walk = (Calendar) now.clone();
        while(past.size() < 12){
            walk.add(Calendar.DAY_OF_MONTH, -1);
            if(tdIsWeekday(walk)) past.add((Calendar) walk.clone());
        }
        for(int d = 0; d < past.size(); d++){
            Calendar day = past.get(d);
            int idx = tdDayIndex(day);
            int slot = 0;
            for(int k = 0; k < cId.size(); k++){
                if(!cDay.get(k)[idx]) continue;
                String start  = dtf.format(tdSetHour(day, 10 + slot).getTime());
                String end    = dtf.format(tdSetHour(day, 11 + slot).getTime());
                String stu    = sId.get((d + k) % sId.size());
                String type   = ((d + k) % 2 == 0) ? "WalkIn" : "Online";
                String status = ((d + k) % 7 == 0) ? "Cancelled" : "Done";
                if(tdBook(usedC, usedS, cId.get(k), stu, start, status))
                    rows.add(new String[]{cId.get(k), stu, start, end, type, "0", status, cSpec.get(k)});
                slot++;
                if(slot > 9) break;
            }
        }

        if(tdIsWeekday(now)){
            int idx = tdDayIndex(now);
            ArrayList<Integer> working = new ArrayList<>();
            for(int k = 0; k < cId.size(); k++) if(cDay.get(k)[idx]) working.add(k);

            if(!working.isEmpty()){
                int full = working.get(working.size() - 1);
                for(int h = 0; h < HOURS.length; h++){
                    String start = dtf.format(tdSetHour(now, 10 + h).getTime());
                    String end   = dtf.format(tdSetHour(now, 11 + h).getTime());
                    String stu   = sId.get(h % sId.size());
                    String type  = (h % 2 == 0) ? "WalkIn" : "Online";
                    if(tdBook(usedC, usedS, cId.get(full), stu, start, "Confirmed"))
                        rows.add(new String[]{cId.get(full), stu, start, end, type,
                                              "0", "Confirmed", cSpec.get(full)});
                }
                ArrayList<Integer> takenHour = new ArrayList<>();
                int w = 0;
                for(int wi = 0; wi < working.size(); wi++){
                    int k = working.get(wi);
                    if(k == full) continue;
                    w++;
                    for(int n = 0; n < 2; n++){
                        int h = (w * 3 + n * 4) % 10;
                        while(takenHour.contains(h)) h = (h + 1) % 10;
                        takenHour.add(h);
                        String start = dtf.format(tdSetHour(now, 10 + h).getTime());
                        String end   = dtf.format(tdSetHour(now, 11 + h).getTime());
                        String stu   = sId.get((h + 1) % sId.size());
                        String type  = (n == 0) ? "WalkIn" : "Online";
                        if(tdBook(usedC, usedS, cId.get(k), stu, start, "Confirmed"))
                            rows.add(new String[]{cId.get(k), stu, start, end, type,
                                                  "0", "Confirmed", cSpec.get(k)});
                    }
                }
                int firstK = working.get(0);
                int ch = 0;
                while(ch < 9 && takenHour.contains(ch)) ch++;
                String cStart = dtf.format(tdSetHour(now, 10 + ch).getTime());
                String cEnd   = dtf.format(tdSetHour(now, 11 + ch).getTime());
                rows.add(new String[]{cId.get(firstK), sId.get((ch + 2) % sId.size()),
                                      cStart, cEnd, "WalkIn", "0", "Cancelled", cSpec.get(firstK)});
            }
        } else {
            System.out.println("Debug: today is a weekend - no walk-in data generated.");
        }

        ArrayList<Calendar> future = new ArrayList<>();
        walk = (Calendar) now.clone();
        while(future.size() < 10){
            walk.add(Calendar.DAY_OF_MONTH, 1);
            if(tdIsWeekday(walk)) future.add((Calendar) walk.clone());
        }
        for(int d = 0; d < future.size(); d++){
            Calendar day = future.get(d);
            int idx = tdDayIndex(day);
            int slot = 0;
            for(int k = 0; k < cId.size(); k++){
                if(!cDay.get(k)[idx]) continue;
                if((d + k) % 2 != 0) continue;
                String start = dtf.format(tdSetHour(day, 10 + slot).getTime());
                String end   = dtf.format(tdSetHour(day, 11 + slot).getTime());
                String stu   = sId.get((d + k) % sId.size());
                if(tdBook(usedC, usedS, cId.get(k), stu, start, "Confirmed"))
                    rows.add(new String[]{cId.get(k), stu, start, end, "Online",
                                          "0", "Confirmed", cSpec.get(k)});
                slot++;
                if(slot > 9) break;
            }
        }

        int made = 0;
        for(int d = 0; d < future.size() && made < 5; d++){
            Calendar day = future.get(d);
            int idx = tdDayIndex(day);
            for(int k = 0; k < cId.size() && made < 5; k++){
                if(!cDay.get(k)[idx]) continue;
                int h = 9 - made;
                String start = dtf.format(tdSetHour(day, 10 + h).getTime());
                String end   = dtf.format(tdSetHour(day, 11 + h).getTime());
                String stu   = sId.get(made % sId.size());
                if(tdBook(usedC, usedS, "null", stu, start, "Pending")){
                    rows.add(new String[]{"null", stu, start, end, "Online",
                                          "0", "Pending", cSpec.get(k)});
                    made++;
                }
            }
        }

        rows.sort((a, b) -> a[2].compareTo(b[2]));
        String today = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
        int q = 0;
        for(String[] r : rows){
            if(r[2].startsWith(today) && r[4].equals("WalkIn") && r[6].equals("Confirmed"))
                r[5] = String.valueOf(++q);
        }
        for(String[] r : rows){
            if(r[2].startsWith(today) && r[6].equals("Confirmed")){
                int endH = Integer.parseInt(r[3].substring(11, 13));
                if(endH <= nowHour){ r[6] = "Done"; r[5] = "0"; }
            }
        }

        ArrayList<String> out = new ArrayList<>();
        int n = 0;
        for(String[] r : rows){
            n++;
            out.add(String.format("B%06d", n) + "," + r[0] + "," + r[1] + "," + r[2] + ","
                  + r[3] + "," + r[4] + "," + r[5] + "," + r[6] + "," + r[7]);
        }
        inputFile("appointment.txt", out, "write");

        String[][] text = {
            {"Student reported exam stress and falling behind on coursework.",
             "Weekly study plan agreed; review progress in two weeks."},
            {"Follow-up session; routine is improving but notes remain disorganised.",
             "Trial a structured note format; bring a study log next time."}
        };
        ArrayList<String> notes = new ArrayList<>();
        for(int k = 0; k < cId.size(); k++){
            int written = 0;
            for(int i = out.size() - 1; i >= 0 && written < 2; i--){
                String[] p = out.get(i).split(",");
                if(!p[1].equals(cId.get(k)) || !p[7].equals("Done")) continue;
                String sName = "Unknown";
                String uLine = null;
                for(String line : readFile("userData.txt")){
                    if(line.startsWith(p[2] + ",")){ uLine = line; break; }
                }
                if(uLine != null) sName = uLine.split(",")[1];
                notes.add(p[0] + "|" + p[1] + "|" + sName + "|" + p[3] + "|"
                        + text[1 - written][0] + "|" + text[1 - written][1]);
                written++;
            }
        }
        notes.sort((a, b) -> a.compareTo(b));
        inputFile("note.txt", notes, "write");

        System.out.println("Debug: generated " + out.size() + " appointments, "
                         + notes.size() + " notes.");
    }

    private static boolean tdIsWeekday(Calendar c){
        int d = c.get(Calendar.DAY_OF_WEEK);
        return d != Calendar.SATURDAY && d != Calendar.SUNDAY;
    }
    private static int tdDayIndex(Calendar c){          // Mon=0 ... Fri=4
        return c.get(Calendar.DAY_OF_WEEK) - 2;
    }
    private static Calendar tdSetHour(Calendar day, int hour){
        Calendar c = (Calendar) day.clone();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
    private static boolean tdBook(ArrayList<String> usedC, ArrayList<String> usedS,
                                String counselor, String student, String start, String status){
        String ck = counselor + "|" + start;
        String sk = student   + "|" + start;
        if(usedS.contains(sk)) return false;
        if(!counselor.equals("null") && usedC.contains(ck)) return false;
        if(!status.equals("Cancelled")){
            usedC.add(ck);
            usedS.add(sk);
        }
        return true;
    }
    
}
