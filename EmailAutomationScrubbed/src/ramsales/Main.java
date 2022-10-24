package ramsales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import ramsales.Interfaces.CreativeABLE;
import ramsales.Interfaces.Sortable;
import ramsales.Interfaces.UnsubHandleAble;
import unsub.UnsubHandlerV1;

public class Main {


    
	
	public static void main(String[] args) {
		setSortable(new Sort());
		setCreative(new CreativeHandler());
		setUnsub(new UnsubHandlerV1());
		


		unsubHandler.handle();

		var parser = new ParseExcel();
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<String> optout = new ArrayList<String>();

		try {
			addresses = parser.getMasterFromExcel("C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\AttachedMedia\\Ram\\Copy Of Email Test List.xlsx");
		} catch (IOException e) {e.printStackTrace();}
		
		try {
			optout = parser.getOptOutFromExcel("C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\CreativeMaterialContainer\\Optoutbook.xlsx");	
			optout = duplicateRemover(optout);
			System.out.println("optout from excel in main: " + optout);
		}catch(IOException e) {e.printStackTrace();}
		
		ArrayList<String> validatedMaster = sorter.validateData(addresses, optout);
		System.out.println("VALIDATED: " + validatedMaster);	
				
		
		
	    creativeHandler.writeHTMLToText();

		
		var pythonHandler = new SnakeCharmer("587", "SCRUBBED", "SCRUBBED", "SCRUBBED", validatedMaster);
        pythonHandler.writeSMTPInfoToFile("c://Users//SCRUBBED//Desktop//Tools Proper Temp//Official Projects//EmailAutomation//Outside-Script//Python_Scripts//SMTPInfo.txt");
        pythonHandler.startScript("C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\Outside-Script\\Python_Scripts", "app.py");
        
        
        
        
		//sender.send();
		
		
		
		
		
	}//Main block
	private static Sortable sorter;
	private static void setSortable(Sortable sorterDepend) {
		sorter = sorterDepend;
	}
	private static CreativeABLE creativeHandler;
	private static void setCreative(CreativeABLE creative) {
		creativeHandler = creative;
	}
	private static UnsubHandleAble unsubHandler;
	private static void setUnsub(UnsubHandleAble unsub) {
		unsubHandler = unsub;
	}
	
	private static ArrayList<String> duplicateRemover(ArrayList<String> input){
		Set<String> remover = new LinkedHashSet<>();
		remover.addAll(input);
		input.clear();
		input.addAll(remover);
		return input;
	}
}//class block