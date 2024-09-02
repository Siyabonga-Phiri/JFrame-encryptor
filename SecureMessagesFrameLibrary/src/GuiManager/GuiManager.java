/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GuiManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class GuiManager {
    
    private String fileName;
    
     public GuiManager(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String readFromFile() throws FileNotFoundException, IOException{
        String data = "",line;
        
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        
        while(line != null){
        
        data = data + line +"\n";
        line = br.readLine();
        
        }
        br.close();
        fr.close();
        return data;
    }
    public void writeToFile(String data) throws IOException
    {
        FileWriter fw = new FileWriter(fileName,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.newLine();
        bw.close();
        fw.close();
    }
  
}
