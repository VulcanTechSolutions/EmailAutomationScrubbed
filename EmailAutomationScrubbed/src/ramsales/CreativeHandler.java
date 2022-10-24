package ramsales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import ramsales.Abstract.Create;
import ramsales.Interfaces.CreativeABLE;

public class CreativeHandler extends Create implements CreativeABLE {

	@Override
	public String locateMaterials(String location) {
	    rawEmailPath = "C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\CreativeMaterialContainer\\HTMLEmails\\email_20221024.html";
	    return rawEmailPath;
	}

	
	//this br should close upon IOException, closing it manually caused an try-catch mess. I don't want to use try with resources.
	public void readHTML() {
	    try {
            Path path = Paths.get(rawEmailPath);
            BufferedReader br = Files.newBufferedReader(path);

        while(true) {
            String imp = br.readLine();
            if(imp == null) {break;}
            rawHTML.add(imp);}System.out.println(rawHTML);
        }catch(IOException e) {System.out.println("no more lines");}
	}
	
	@Override
	public void writeHTMLToText() {
	    
	    locateMaterials("placeholder");
	    readHTML();
	    concatHTMLHeader("Greetings ");
	    
        try {
            BufferedWriter bw = Files.newBufferedWriter(Paths.get("C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\Outside-Script\\Python_Scripts\\HTMLFinal.txt"));
            
            for(String line : rawHTML) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {e.printStackTrace();}

	}
	@Override
	public void concatHTMLHeader(String subject) {
	    
	    HTMLHeader.add("Subject:" + subject);
	    HTMLHeader.add("Content-type: text/html ");
	    HTMLHeader.add("MIME-Version: 1.0 ");
	    HTMLHeader.add("From: vulcantechimp@gmail.com ");
	    
	    for(String line : HTMLHeader) {
	        rawHTML.add(0, line);
	    }
	    
	    
	}
	
		
	
}//class block
