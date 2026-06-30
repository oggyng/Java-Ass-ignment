//  This will be the main menu
//  So I'll just write down the requirements here
//=================================== Rules =========================================
//  Every Parent class for roles data should ended up returning in ArrayList form
//===================================================================================

//=================================  CheatSheet  ====================================
//  Capslock text r the one u should type urself (means dont copy)
//-------------- ArrayList -------------
//  ArrayList<DATATYPE> ARRAYNAME = new ArrayList<>(); --creating new array object
//  ARRAYNAME.get(INDEX) --get data with the index position
//  ARRAYNAME.add(DATA)  --append data to array
//  ARRAYNAME.size()  --length of array
//  ARRAYNAME.remove(INDEX) --remove data at INDEX
//  ARRAYNAME.contains(DATA)  --check if array consists of DATA (returns true id yes)
//  ARRAYNAME.set(INDEX, DATA) --replace data at INDEX
//--------------------------------------

//--------------- AWT Layouts --------------
//  H is Horizontal, V is Vertical
//  Setup layout: setLayout(new LAYOUTYOUWANT());

//  FlowLayout(int ALIGN, int HGAP, int VGAP) --Align includes (FlowLayout.LEFT .RIGHT .CENTER)
//  BorderLayout(int HGAP, int VGAP)
//  GridLayout(int ROWS, int COLS, int HGAP, int VGAP)
//--------------- Adding Components to Layout -----------------
//  add(COMPONENT); --FlowLayout
//  add(COMPONENT, BORDERLAYOUT); --BorderLayout, includes (BorderLayout.NORTH .SOUTH .WEST .EAST .CENTER)
//  add(COMPONENT); --GridLayout (automatically align like table from left to right, up to down)
//===================================================================================
package assignment;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


public class Assignment extends Frame implements ActionListener {

    TextField nameField, pwField;
    Button signInBut, signUpBut;
    Label status;
    int tries = 3;

    final String loginFile = "loginData.txt";

    public Assignment() {
        setTitle("Login");
        setSize(1200, 800);
        setLayout(new BorderLayout(10, 10));
        
        // User login menu panel
        Panel sect = new Panel(new GridLayout(0, 1, 5, 5));
        sect.add(new Label("Username:"));
        nameField = new TextField();
        sect.add(nameField);
        
        sect.add(new Label("Password:"));
        pwField = new TextField();
        sect.add(pwField);
       
        status = new Label("                                                                         ");
        sect.add(status);
        
        signInBut = new Button("Sign In");
        signInBut.addActionListener(this);
        sect.add(signInBut);


        
        Panel center = new Panel(new GridBagLayout());
        center.add(sect);
        add(center, BorderLayout.CENTER);

//        // ===== CENTER: scrollable list of records =====
//        studentList = new List();
//        add(studentList, BorderLayout.CENTER);
//
//        // ===== SOUTH: button row (FlowLayout) + status line =====
//        Panel buttons = new Panel(new FlowLayout());
//        addBtn    = new Button("Add");
//        deleteBtn = new Button("Delete");
//        clearBtn  = new Button("Clear");
//        saveBtn   = new Button("Save");
//        loadBtn   = new Button("Load");
//        // register one listener (this) for every button
//        addBtn.addActionListener(this);
//        deleteBtn.addActionListener(this);
//        clearBtn.addActionListener(this);
//        saveBtn.addActionListener(this);
//        loadBtn.addActionListener(this);
//        buttons.add(addBtn);
//        buttons.add(deleteBtn);
//        buttons.add(clearBtn);
//        buttons.add(saveBtn);
//        buttons.add(loadBtn);
//
//        status = new Label("Ready.");
//
//        // nest two panels so buttons sit above the status line
//        Panel south = new Panel(new BorderLayout());
//        south.add(buttons, BorderLayout.CENTER);
//        south.add(status, BorderLayout.SOUTH);
//        add(south, BorderLayout.SOUTH);

        // make the X button close the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        setVisible(true);
    }

    /* One method handles every button. We check the source
       to decide what to do. */
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == signInBut){
            signIn();
        }
    }

    void signIn() {
        String name   = nameField.getText().trim();
        String password = pwField.getText().trim();

        ArrayList<String> userList = Functions.readFile(loginFile);
        for(String line : userList){
            String[] part = line.split(",");
            if(name.equals(part[0]) && password.equals(part[1])){
                //----------------------------------------------
                System.out.println("peak"); // user menu
                //----------------------------------------------
            }
        }
        
        status.setText("Wrong username or password, please try again!");
            tries--;
            if(tries<=0){
                status.setText("Too many login attempts, self destruct activated!");
                dispose();
            }
            
    }
//
//    void deleteStudent() {
//        int i = studentList.getSelectedIndex();   // -1 if nothing selected
//        if (i == -1) {
//            status.setText("Select a student to delete.");
//            return;
//        }
//        Student removed = students.remove(i);
//        studentList.remove(i);
//        status.setText("Deleted: " + removed.name);
//    }
//
//    void clearForm() {
//        idField.setText("");
//        nameField.setText("");
//        marksField.setText("");
//    }
//
//    // ===== FILE I/O: write every record to a text file =====
//    void saveToFile() {
//        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
//            for (Student s : students) {
//                pw.println(s.toCSV());
//            }
//            status.setText("Saved " + students.size() + " records to " + FILE);
//        } catch (IOException ex) {
//            status.setText("Save failed: " + ex.getMessage());
//        }
//    }
//
//    // ===== FILE I/O: read records back from the text file =====
//    void loadFromFile() {
//        File f = new File(FILE);
//        if (!f.exists()) {
//            status.setText("No saved file found yet.");
//            return;
//        }
//        students.clear();
//        studentList.removeAll();
//        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                if (line.trim().isEmpty()) continue;
//                String[] p = line.split(",");
//                if (p.length < 3) continue;
//                Student s = new Student(p[0], p[1], Double.parseDouble(p[2]));
//                students.add(s);
//                studentList.add(s.toString());
//            }
//            status.setText("Loaded " + students.size() + " records.");
//        } catch (IOException | NumberFormatException ex) {
//            status.setText("Load failed: " + ex.getMessage());
//        }
//    }

    public static void main(String[] args) {
        new Assignment();
    }
}
