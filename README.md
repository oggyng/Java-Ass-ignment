# Java Assignment

---

## Introduction
A Counselling Management System with four roles: **Admin**, **Counselor**, **Receptionist**, **Student**.

So apparently the cheatsheet for AWT is only useful on exam but useless on assignment :(

Currently in:
**Progressing + Suffering = creating a bunch of trash code (we called it 屎山代码)**

---

## Progress

### ✅ Done
- **Login Interface** — show/hide password, self-destruct after 3 failed attempts, role-based routing
- **Welcome Page** — intro screen before login
- **switchTo(PANEL) / switchContent(PANEL)** — screen navigation system
- **All Main Panels**
- **Object for Roles** — Admin, Receptionist, Counselor, Student, Appointment
- **Admin — Manage Accounts (AAccountPanel)** — Create, Read, Update, Delete for Counselor/Receptionist accounts
- **Admin — Reports (AReportPanel)** — appointment statistics with Daily/Weekly/Monthly/Yearly filter
- **Admin — Staff Roster (AStaffPanel)** — view and manage counselor roster and specialisation
- **Admin — Appointment Statistics (AAppointPanel)**
- **Receptionist — Accounts (RAccountPanel)** — manage student accounts

### 🔄 In Progress
- **Receptionist — Appointments (RAppointPanel) 80%** — create Walk-in and Online appointments, specialism filter, counselor availability check, date range (2 months from today)
- **Receptionist — Assign Counselor (RAssignPanel)**
- **Receptionist — Queue (RQueuePanel)**

### ⬜ Not Started
- All **Counselor** panels (CAppointPanel, CRecomPanel, CRecordPanel, CRosterPanel)
- All **Student** panels (SAppointPanel, SHistoryPanel, SProfilePanel, SQueuePanel)

### Things you might need to look up to:
- Functions.filterData, probably change it or add another to equals instead of containing same target because names might contains other name in the name (will ignore if lazy).
---

## Assignment Rules

- Every **Load from file** or **Save to file** should end up returning in `ArrayList` form.
- Imported APIs:
  ```java
  import java.awt.*;          // AWT interface stuff
  import java.awt.event.*;    // AWT event handling
  import java.io.*;           // File I/O
  import java.util.ArrayList; // ArrayList
  import java.util.Date;      // Date
  import javax.swing.*;       // Swing interfaces
  ```
- Layout size: **1200x800** (set in each main panel properties)
- Each page (interface) should be in individual Java class file (JPanel Form) — easier to switch
- When a new user is created, the system automatically adds 1 to the max ID. Deleted users keep their ID — new users will not reuse old IDs.
- When an appointment is updated (date/time change), it keeps the same appointment ID. Adding or deleting follows the same ID rule.
- Every appointment takes exactly **1 hour**. Appointments cannot be before **10:00** or after **20:00** (10 time slots).
- Self-destruct after 3 failed login attempts ✅
- For cheat sheets, words in **CAPSLOCK** are placeholders — insert your own values, do not copy them directly.

---

## Files

| File | Format |
|---|---|
| `loginData.txt` | `UID, username, password, role` |
| `userData.txt` | `UID, name, gender, DoB, email` |
| `appointment.txt` | `BookingID, CounselorID, StudentID, startDateTime, endDateTime, bookingType, queueNumber, status, specialism` |
| `cProfile.txt` | `RosterID, CounselorID, Monday, Tuesday, Wednesday, Thursday, Friday, Specialisation` |
| `note.txt` | `NoteID, AppointmentID, CounselorID, StudentID, notes, recommendations` |

### Appointment Status Values
| Status | Meaning |
|---|---|
| `Pending` | Student booked online, awaiting receptionist approval |
| `Confirmed` | Receptionist confirmed / Walk-in created |
| `Cancelled` | Cancelled by anyone |
| `Done` | Completed appointment |

### Appointment Business Rules
- Walk-in appointments only exist on **today's date** — no future walk-ins
- Walk-in appointments get a **queue number** (daily reset, starts from 1)
- Online bookings have queue number **0** (no queue)
- Cancelled walk-ins have queue number **0**
- Student online bookings have `null` counselorID and status `Pending` until assigned
- Working hours: **10:00 – 20:00** daily

---

## Object Classes

| Class | Extends | Purpose |
|---|---|---|
| `ORoleParent` | — (abstract) | Base class for all roles. Common fields: id, name, gender, DoB, email |
| `OAdmin` | `ORoleParent` | Admin role object |
| `OCounselor` | `ORoleParent` | Counselor role object |
| `OReceptionist` | `ORoleParent` | Receptionist role object |
| `OStudent` | `ORoleParent` | Student role object |
| `OAppointment` | — | Appointment object. Fields: bookingId, counselorId, studentId, startTime, endTime, bookingType, queueNumber, status |

---

## Functions Reference

| Method | What it does |
|---|---|
| `readFile(FILENAME)` | Reads file, returns `ArrayList<String>`. Skips blank lines. |
| `inputFile(FILENAME, DATA, MODE)` | `"write"` = overwrite file, `"append"` = add to end |
| `filterData(DATA, TARGET, INDEX)` | Filter ArrayList by target value at column index. Use `-1` to check all columns. |
| `filterID(ID, FILENAME)` | Returns the full line from file matching the given ID |
| `removeData(ID, FILENAME)` | Removes line from file by ID |
| `checkDayClash(DATE, COUNSELORID)` | Returns available time slots for a counselor on a given date |
| `checkDate(YEAR, MONTH)` | Returns number of days in a given month/year (handles leap year) |
| `StringtoDate(STRING)` | `"yyyy-MM-dd"` → `Date` |
| `StringtoDateTime(STRING)` | `"yyyy-MM-dd HH:mm"` → `Calendar` |
| `DatetoString(DATE)` | `Date` → `"yyyy-MM-dd"` |
| `DateTimetoString(CALENDAR)` | `Calendar` → `"yyyy-MM-dd HH:mm"` |
| `DatetoDateTime(DATE, TIME)` | Combines `Date` + time string → `Calendar` |
| `DateTimetoDate(CALENDAR)` | `Calendar` → `Date` (strips time) |
| `DoBtoList(DOB)` | `"yyyy-MM-dd"` → `String[]` {year, month, day} for picker population |
| `ListtoDoB(ARRAY)` | `String[]` {year, month, day} → `"yyyy-MM-dd"` |
| `ageRange()` | Returns `ArrayList<Integer>` of years from current year back 80 years |
| `updateAppointFile()` | Updates appointment statuses (marks past confirmed appointments as Done) |

---

## Tips for the Assignment

- Double click components while designing the panel to directly access the action listener
- You can put panels inside panels — useful for sidebars, headers, footers
- Use `loading = true` before `removeAllItems()` on a combobox to prevent action listeners from firing during population
- Always populate DayList before YearList in the constructor to avoid NullPointerException chain reactions
- Use `MonthList.getSelectedIndex() + 1` instead of parsing the month name string for cleaner date building
- Use `String.valueOf()` instead of casting `(String)` on combobox items that contain integers (DayList, YearList)
- `model.setRowCount(0)` to clear all rows from a table without removing columns
- `jTable1.convertRowIndexToModel(visualRow)` — always convert row index after adding TableRowSorter
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
| `ARRAYNAME.contains(DATA)` | Check if the list has DATA — returns **boolean** |
| `ARRAYNAME.set(INDEX, DATA)` | Replace data at the given index |

---

## Calendar Cheatsheet

```java
Calendar cal = Calendar.getInstance(); // current date and time

// get parts
cal.get(Calendar.YEAR)
cal.get(Calendar.MONTH)          // 0-based! January = 0, December = 11
cal.get(Calendar.DAY_OF_MONTH)
cal.get(Calendar.HOUR_OF_DAY)
cal.get(Calendar.MINUTE)
cal.get(Calendar.DAY_OF_WEEK)    // Sunday=1, Monday=2 ... Saturday=7

// add / subtract
cal.add(Calendar.DAY_OF_MONTH, 7);    // +7 days
cal.add(Calendar.MONTH, 2);           // +2 months
cal.add(Calendar.HOUR_OF_DAY, -1);    // -1 hour

// clone before modifying
Calendar copy = (Calendar) cal.clone();
copy.add(Calendar.HOUR_OF_DAY, 1);    // original unchanged

// set specific time
cal.set(Calendar.HOUR_OF_DAY, 23);
cal.set(Calendar.MINUTE, 59);
cal.set(Calendar.SECOND, 0);

// get last day of month
cal.getActualMaximum(Calendar.DAY_OF_MONTH);
```

---

## Swing and AWT Stuffs

---

### Modifying Table Model
```java
import javax.swing.table.DefaultTableModel;

private DefaultTableModel model = new DefaultTableModel();
private String[] columnName = {COLUMNTITLE1, COLUMNTITLE2, COLUMNTITLE3, COLUMNTITLE4};

public JAVACLASSCONSTRUCTOR(){
    model.setColumnIdentifiers(columnName);
}
```
Then double-click the table in design and choose `Customize Code`

Select the second `default code` and remove everything and input `model`

Should look like this:
```java
jTable1.setModel(model);
```

**To add content into the table:**
```java
model.addRow(CONTENT); // content should be in array/list format
```

**To delete content from a table:**
- Add `private int row = -1;` to the class
- Double-click the table in design → `Events` → `Mouse` → `mouseReleased`
- Insert: `row = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());`
- Get value of the row: `String NAME = String.valueOf(model.getValueAt(row, COLUMNINDEX));`
- Remove a row: `model.removeRow(row);`

**To update content from table:**
```java
model.setValueAt(NEWCONTENT, row, COLUMNINDEX);
```

**To clear all rows:**
```java
model.setRowCount(0);
```

**To enable column sorting:**
```java
TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
jTable1.setRowSorter(sorter);
// always use convertRowIndexToModel when reading selected row after this
```

**Note!!!**
- Always do `if(row == -1) return;` before executing button code
- Reset with `row = -1;` after every button action to avoid stale index errors

---

### ComboBox (JComboBox)

```java
// add items
comboBox.addItem(VALUE);
comboBox.removeAllItems();

// read selected
String s   = (String)  comboBox.getSelectedItem();  // if String items
int    i   = (int)     comboBox.getSelectedItem();   // if Integer items
String str = String.valueOf(comboBox.getSelectedItem()); // safe for any type

// set selected
comboBox.setSelectedIndex(0);
comboBox.setSelectedItem("VALUE");

// count items
comboBox.getItemCount();
```

---

### Pop-up Windows
```java
import javax.swing.JOptionPane;

JOptionPane.showMessageDialog(FRAME, TEXT);                          // info message
JOptionPane.showMessageDialog(FRAME, TEXT, TITLE, JOptionPane.ERROR_MESSAGE); // error
JOptionPane.showConfirmDialog(FRAME, TEXT);                          // returns 0=Yes, 1=No, 2=Cancel
```

---

### Close Windows
```java
FRAME.dispose();           // close window, system keeps running
FRAME.setVisible(false);   // hide window, still running in background
System.exit(0);            // close entire system
```

---

### Layouts

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
| `BorderLayout.CENTER` | Middle (fills all remaining space) |

**Note:** CENTER always fills all leftover space. When WEST panel is hidden, set its preferred size to `(0,0)` instead of `setVisible(false)` — BorderLayout reserves space even for invisible components.

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

Components fill in automatically left to right, top to bottom. Use `0` for rows or cols to auto-expand.

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
