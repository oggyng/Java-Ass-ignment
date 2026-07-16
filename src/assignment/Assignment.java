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
import javax.swing.*;


public class Assignment extends JFrame{
    
    // this is where you should put every panel
    public LoginPanel loginPanel;


    

    public Assignment() {
        setTitle("Login");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        loginPanel = new LoginPanel(this);
        
        
        
        switchTo(loginPanel);
        setVisible(true);
    }

    public final void switchTo(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    
    public void switchContent(JPanel panel, JPanel contentPanel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            WelcomePage wp = new WelcomePage();
            wp.setVisible(true);
        });
        
    }
    
    public void showError(){
        JOptionPane.showMessageDialog(this,"Sum Ting Wong");
    }
}
