/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SecureMessagesFrame;

import Encryptor.Encryptor;
import GuiManager.GuiManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author user
 */
public class SecureMessagesFrame extends JFrame{
    
    private JMenuBar mainMenu;
    
    private JMenu menu;
    
    private JMenuItem openFileMenuItem;
    private JMenuItem encryptMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem clearMenuItem;
    private JMenuItem exitMenuItem;
    
    private JFrame mainFrame;
    
    private JPanel mainPnl;
    private JPanel headPnl;
    private JPanel menuPnl;
    private JPanel titlePnl;
    private JPanel plainMessagePnl;
    private JPanel encryptedMessagePnl;
    private JPanel messagePnl;
    
    private JLabel titleLbl;
    
    private JTextArea plainMessageTxtArea;
    private JTextArea encryptedMessageTxtArea;
    
    private JScrollPane plainMessageScrollPane;
    private JScrollPane encryptedMessageScrollPane;
    
    
    
    
    public SecureMessagesFrame(){
    
    
        mainMenu = new JMenuBar();
        
        menu = new JMenu("File");
       
        
        openFileMenuItem = new JMenuItem("Open File....");
        openFileMenuItem.addActionListener(new jMenuOpenFile());
        
        
        encryptMenuItem = new JMenuItem("Encrypt message....");
        encryptMenuItem.addActionListener(new encryptMessage());
        
        saveMenuItem = new JMenuItem("Save encrypted message....");
        saveMenuItem.addActionListener(new saveEncrypted());
        
        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(new clearContent());
        
        
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new jExitProgram());
        
        menu.add(openFileMenuItem);
        menu.add(encryptMenuItem);
        menu.add(saveMenuItem);
        menu.addSeparator();
        menu.add(clearMenuItem); 
        menu.add(exitMenuItem);
        
        
        mainMenu.add(menu);
        
       
        
        menuPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPnl.setBackground(Color.BLUE); 
       
        
        menuPnl.add(mainMenu);
        
        titleLbl = new JLabel("Message Encryptor");
        titleLbl.setFont(new Font(Font.SANS_SERIF,Font.BOLD+Font.ITALIC,30));
        titleLbl.setBorder(new BevelBorder(BevelBorder.RAISED));
        titleLbl.setForeground(Color.BLUE);
        
        
        
        titlePnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        titlePnl.add(titleLbl);
        
       
        
        
        plainMessageTxtArea = new JTextArea(12,24);
        encryptedMessageTxtArea = new JTextArea(12,24);
        
        plainMessageTxtArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Plain Message"));
        
        plainMessageScrollPane =  new JScrollPane(plainMessageTxtArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        encryptedMessageTxtArea.setBorder(new TitledBorder(new LineBorder(Color.BLACK,1),"Encrypted Message"));
        
        encryptedMessageScrollPane =  new JScrollPane(encryptedMessageTxtArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        plainMessagePnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
        encryptedMessagePnl = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        messagePnl = new JPanel(new FlowLayout());
        
        plainMessagePnl.add(plainMessageScrollPane);
        encryptedMessagePnl.add(encryptedMessageScrollPane);
        
        messagePnl.add(plainMessagePnl);
        messagePnl.add(encryptedMessagePnl);
        
        
        
      
        mainPnl = new JPanel(new BorderLayout());
        
        mainPnl.add(menuPnl,BorderLayout.NORTH);
        mainPnl.add(titlePnl,BorderLayout.CENTER);
        mainPnl.add(messagePnl,BorderLayout.SOUTH);
       
       
        
        
        
        
        
        add(mainPnl);
        
       
    setTitle("Secure Messages");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(500,550);
    setVisible(true);
    
    }
private class jMenuOpenFile implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
        JFileChooser choser = new JFileChooser();
        int returnVal = choser.showSaveDialog(getRootPane());
        
        if(returnVal == JFileChooser.APPROVE_OPTION){
        
        String fileName = choser.getSelectedFile().getAbsolutePath();
        JOptionPane.showMessageDialog(null,fileName);
        
        GuiManager gm = new GuiManager(fileName);
            try {
                
                String line = gm.readFromFile();
                plainMessageTxtArea.setText(line);
                
            } catch (IOException ex) {
                Logger.getLogger(SecureMessagesFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
        }
       
        }


}
private class encryptMessage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
           Encryptor obj = new Encryptor(plainMessageTxtArea.getText());
           
           encryptedMessageTxtArea.setText(obj.encryptMessage());
            
         }

}

private class clearContent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          
        encryptedMessageTxtArea.setText("");
         plainMessageTxtArea.setText("");
        
        }

}
private class jExitProgram implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                
                System.exit(0);
                
              }
        }



private class saveEncrypted implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
         
              //save to file
            String data = encryptedMessageTxtArea.getText();
            
            GuiManager gm = new GuiManager("Saved Encryption.txt");
            try {
                gm.writeToFile(data);
                 JOptionPane.showMessageDialog(getRootPane(), "Saved to the file"); 
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(getRootPane(), ex.getMessage());
            }
        }
    }

        
        }







    
    

