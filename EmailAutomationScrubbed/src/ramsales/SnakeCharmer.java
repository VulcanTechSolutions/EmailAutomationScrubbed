package ramsales;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import ramsales.Abstract.Python;

public class SnakeCharmer extends Python{
    

    
    
    public SnakeCharmer(String smtpPort, String sendFrom, String username, String password, ArrayList<String> sendTo) {
        //this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.sendFrom = sendFrom;
        this.username = username;
        this.password = password;
        this.sendTo = sendTo;
        //this.HTMLPath = HTMLPath;
    }


    
    
    public void writeSMTPInfoToFile (String pythonFilePath) {

        addressListLength = "" + sendTo.toArray().length;
        try {
            BufferedWriter bw = Files.newBufferedWriter(Paths.get(pythonFilePath));
            //bw.write(smtpServer);
            //bw.newLine();
            bw.write(smtpPort);
            bw.newLine();
            
            bw.write(sendFrom);
            bw.newLine();
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.newLine();

            bw.write(addressListLength);
            bw.newLine();
           
            
            for(String address : sendTo) {
                bw.write(address);
                bw.newLine();
            }

                
            bw.close();
            
        }catch (IOException e) {System.out.println("updating the python info file didn't work");}  
    }
    
    
    public void startScript(String appPyPath, String appName) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(new String[] {"cmd.exe", "/c cd " + appPyPath + " & python " + appName});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}//class block
