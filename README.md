# Java Assignment

---

## Introductions
Just an assignment to create a Counseling Management System with four roles (Admin, Counselor, Receptionist. Students)

Currently in: 
**Stuck, unable to continue until my question has resolved**

---

## Assignment Rules

 - Every **Load from file** or **Save to file** should end up returning in `ArrayList` form.
 - Imported APIs:
   ```java
   import java.awt.*;
   import java.awt.event.*;
   import java.io.*;
   import java.util.ArrayList;
   ```
 - Layout size: 1200x800
 - Self-destruct button (available)
 - For cheat sheets, words in **CAPSLOCK** are just placeholders — insert ur own values. Do not copy them directly.

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

## AWT Layouts

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
