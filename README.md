# Java Assignment

---

## Introductions
Just an assignment to create a Counseling Management System with four roles (Admin, Counselor, Receptionist. Students)

So apparently the cheatsheet for AWT is only useful on exam but useless on assignment :(

Currently in: 
**Progressing**

What I've done (Linkin Park reference):
 - **readFile(FILENAME)** `It reads file ofc`
 - **inputFile(FILENAME,MODE)** `It overwrites file for MODE=false, append data to file for MODE=true`
 - **Login Interface** `Very cool, it can show or hide password, and so selft destruct when password is incorrect for three times`
 - **Counselor Interface 30%** `Just each panels button, not yet added header and their respective interface swapping`
 - **roleObject class** `Still configuring, need time to settle that, if no problem that will most likely be every role's property class`
 - **switchTo(PANEL) and switchContent(PANEL)** `This is where you switch between interfaces`
 - **filterData(DATAARRAY,TARGET,INDEX)** `Filter data to ArrayList based on target and index column (input -1 for index if want to check all column)`
 - **filterID(ID,FILENAME)** `Return a string line of user data based on id`
 - **functions that convert date, calendar and string to each other**
 - **removeData(ID, FILENAME)** `remove data (line) from file based on ID and filename.`
 - **checkDayClash(DATE)** `Checks if the date object provided clashes to other appointments, returns time that did not clash.`

Current bugs:
 - able to create two or more same user in admin add acc
 - some readFile line problem (read extra line that put in table)

---

## Assignment Rules

 - Every **Load from file** or **Save to file** should end up returning in `ArrayList` form.
 - Imported APIs:
   ```java
   import java.awt.*; // Awt interface stuff
   import java.awt.event.*; // Awt interface stuff
   import java.io.*; // User input (I think won't use it
   import java.util.ArrayList; // Array List
   import java.util.Data; // Date
   import java.swing.*; // Swing interfaces
   ```
 - Layout size: 1200x800 (set in each main panel properties)
 - Each page (interface) should be in individual java class file (JPanel form), easier to switch
 - When a new user created, the system will automatically adds 1 to the max id and it will be the user id for the new user. Deleted user will remains the id and other new user will not replace the old id.
 - However when an appointment needs to be updated (such as changing date or time), they will use the same appointment id, but adding or deleting appointment will be same as use.
 - Every appointment should takes only 1 hr, and appointment should not exist before 10am and after 8pm (10hrs).
 - Self-destruct (available)
 - For cheat sheets, words in **CAPSLOCK** are just placeholders — insert ur own values. Do not copy them directly.

---

## Files
 - **loginData** : `UID, username, password, roles`
 - **userData** : `UID, username, gender, DOB, email`
 - **appointment** : `AppointID, UID(Counselor), UID(Student), startDateTime, endDateTime, status(Approved, Declined, PendingAdd, PendingDelete, PendingUpdate, Done)`

---

## Tips for the Assignment

- Double click components while designing the panel can directly leads to the action listener for the component
- You can put panels inside panel, therefore features like sidebars or header footer are able to make easily
- Headache now imma continue to think tmr...

---

## ArrayList

Create a new ArrayList:
```java
ArrayList<DATATYPE> ARRAYNAME = new ArrayList<>();
```

| Method | What it does |
|---|---|
| `ARRAYNAME.get(INDEX)` | Get data at the index position |
| `ARRAYNAME.add(DATA)` | Append data to the end of the list |
| `ARRAYNAME.size()` | Get the number of items in the list |
| `ARRAYNAME.remove(INDEX)` | Remove data at the given index |
| `ARRAYNAME.contains(DATA)` | Check if the list has DATA — returns in **boolean** form |
| `ARRAYNAME.set(INDEX, DATA)` | Replace data at the given index |

---

## Swing and AWT Stuffs

---

### Modifying Table Model
```java
import javax.swing.table.DefaultTableModel;

private DefaultTableModel model = new DefaultTableModel();
private String[] columnName = {COLUMNTITLE1,COLUMNTITLE2,COLUMNTITLE3,COLUMNTITLE4};



public JAVACLASSCONSTRUCTOR(){
     model.setColumnIdentifiers(columnName);
}
```
Then double-click the table in design and choose `Customize Code`

Select the second `default code` and remove everything and input `model`

Should be looks like this:
```java
jTable1.setModel(model);
```

**To add content into the table, just use:**
```java
model.addRow(CONTENT); // The content should be in list format!
```

**To delete content from a table:**

 - add this line to the java class file `private int row = -1;`
 - Double-click the table in design and select `Events` -> `Mouse` -> `mouseReleased`
 - insert this line: `row = jTable1.getSelectedRow();`
 - To get value of the row: `String NAME = String.valueOf(model.getValueAt(row,COLUMNINDEX));`
 - To remove a row: `model.removeRow(row);`

**To update content from table:**
 - Simply do: `model.setValueAt(NEWCONTENT,row,COLUMNINDEX)`


**Note!!!**
 - do if(row==-1) before executing button code and call `row = -1;` every time a button performed to avoid error
---

### Pop-up windows
Import this: `import javax.swing.JOptionPane;`

This is where you want to do pop-up notifications for example `Sum Ting Wong!`:
```java
JOptionpane.showMessageDialog(FRAME, TEXT);
```

This is where you want to do confirmation notifications for example `Are you sure you want to delete?`:
```java
JOptionpane.showConfirmDialog(FRAME, TEXT); //will return 0,1,2 (Yes,No,Cancel)
```
---

### Close windows
So there will be three types of method to close your window, each with different functions
```java
FRAME.dispose(); // Close the window, but the system will still run if there are other frame running in the background
FRAME.setVisible(false); // Make the window invisible, but still running in the background
System.exit(0); // Close the whole system (why don't just do Alt-F4)

```

> `HGAP` = Horizontal gap, `VGAP` = Vertical gap

Set a layout on your Frame or Panel:
```java
setLayout(new LAYOUTYOUWANT());
```

### FlowLayout
Arranges components left to right, wraps to next line when full.
```java
setLayout(new FlowLayout(ALIGN, HGAP, VGAP));
```

| Alignment option | Effect |
|---|---|
| `FlowLayout.LEFT` | Align components to the left |
| `FlowLayout.RIGHT` | Align components to the right |
| `FlowLayout.CENTER` | Align components to the center |

Add components:
```java
add(COMPONENT);
```

**Example:**
```java
setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
add(new Button("Save"));
add(new Button("Cancel"));
```

<img width="428" height="442" alt="image" src="https://github.com/user-attachments/assets/12fc1101-3e56-47d7-9f8a-ec3547105a9f" />


---

### BorderLayout
Divides the window into 5 fixed regions.
```java
setLayout(new BorderLayout(HGAP, VGAP));
```

```
[ NORTH  ]
[ WEST | CENTER | EAST ]
[ SOUTH  ]
```

Add components with a region:
```java
add(COMPONENT, BorderLayout.REGION);
```

| Region option | Position |
|---|---|
| `BorderLayout.NORTH` | Top |
| `BorderLayout.SOUTH` | Bottom |
| `BorderLayout.WEST` | Left |
| `BorderLayout.EAST` | Right |
| `BorderLayout.CENTER` | Middle |

**Example:**
```java
setLayout(new BorderLayout(5, 5));
add(new Button("Top"),    BorderLayout.NORTH);
add(new Button("Bottom"), BorderLayout.SOUTH);
add(new Button("Left"),   BorderLayout.WEST);
add(new Button("Right"),  BorderLayout.EAST);
add(new TextArea(),       BorderLayout.CENTER);
```
<img width="431" height="441" alt="image" src="https://github.com/user-attachments/assets/18da0595-dd67-49f0-8967-b7f74b79082f" />

---

### GridLayout
Divides the window into equal-sized cells arranged in rows and columns.
```java
setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
```

Components fill in automatically left to right, top to bottom.

Add components:
```java
add(COMPONENT);
```

**Example:**
```java
setLayout(new GridLayout(2, 3, 5, 5));
// fills like this:
// [ Btn1 ] [ Btn2 ] [ Btn3 ]
// [ Btn4 ] [ Btn5 ] [ Btn6 ]
add(new Button("1"));
add(new Button("2"));
add(new Button("3"));
add(new Button("4"));
add(new Button("5"));
add(new Button("6"));
```
<img width="432" height="440" alt="image" src="https://github.com/user-attachments/assets/97d62af6-71b0-432c-a978-49cc5716b6ff" />

---

## Layout Comparison

| Layout | Best used for | Components fill |
|---|---|---|
| `FlowLayout` | Buttons in a row | Left → right, wraps |
| `BorderLayout` | Main window structure | 5 fixed regions |
| `GridLayout` | Forms, calculators | Equal cells, row by row |

---
### Stay tune for more... (Can provide some ideas to https://github.com/oggyng/Java-Ass-ignment/issues if able :)
