package assignment;

import java.awt.*;
import javax.swing.*;


public class Main extends JFrame{
    

    public LoginPanel loginPanel;


    

    public Main() {
        setTitle("Login");
        setSize(1200, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        loginPanel = new LoginPanel(this);
        
        // Uncomment the next line to regenerate test case data based on current date!
//        Functions.generateTestData();

        Functions.updateAppointFile();
        
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
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
            UIManager.put("Button.arc", 20);
            UIManager.put("Component.arc", 10);
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("defaultFont", new Font("Bell MT", Font.BOLD, 14));
            UIManager.put("Component.focusColor", new Color(70, 130, 180));
            UIManager.put("Table.background", new Color(102,153,0));
            UIManager.put("TableHeader.background", new Color(51,102,0));
            UIManager.put("Table.selectionBackground",new Color(153,255,153));
            UIManager.put("Table.selectionForeground",new Color(0,51,51));
//            UIManager.put("Button.background",new Color(102,153,0));
//            UIManager.put("TextField.background",new Color(102,153,0));
//            UIManager.put("ComboBox.background",new Color(102,153,0));
        } catch(Exception e) {

        }
        java.awt.EventQueue.invokeLater(() -> {
            WelcomePage wp = new WelcomePage();
            wp.setVisible(true);
        });
        
    }
    
    public void showError(){
        JOptionPane.showMessageDialog(this,"Sum Ting Wong");
    }
}
