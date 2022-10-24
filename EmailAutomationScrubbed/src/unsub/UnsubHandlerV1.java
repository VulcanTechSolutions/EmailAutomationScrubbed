package unsub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import ramsales.ParseExcel;
import ramsales.Interfaces.UnsubHandleAble;

public class UnsubHandlerV1 implements UnsubHandleAble{

	
	private String mainTargetFolderPath = "C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\CreativeMaterialContainer\\Optout\\vulcantechimp@gmail.com";
	private String counterPath = "C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\CreativeMaterialContainer\\Optout\\OptoutCounter.txt";
	private int outerCount;
	private ArrayList<String> optOutListRaw = new ArrayList<String>();
	private String optOutExcelFilePath = "C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\CreativeMaterialContainer\\Optoutbook.xlsx";	
	//go into "submissions ram" of most recent (according to counter) and gets counter for submission filenames
	//for each loop, goes into submissions again and opens each file as .txt, searches in file for client's email address, adds that value as string to return list

	@Override
	public void handle() {

		var systools = new SystoolsHandler();
		systools.runSystoolsScript();
		
		var path = outerCountCheck();
		
		String[] list = getFilenames(path);
		
		for(String file : list) {
			optOutListRaw.add(trimOptOutEmail(readFile(path+"\\"+file).toString()));
		}
		
		var parser = new ParseExcel();
		
		try {
			parser.writeUpdatedOptOutList(optOutExcelFilePath , optOutListRaw);
		} catch (java.io.IOException e) {e.printStackTrace();}
		
		var countCountula = new CounterHandler();
		countCountula.setPath(counterPath);
		countCountula.updateCounterFile(outerCount++);
	}//Handle Block
	
	public String outerCountCheck() {
		var countCountula = new CounterHandler();
		countCountula.setPath(counterPath);
		outerCount = countCountula.readCounterFile();
		String addImp = "" + outerCount;
		String toAdd = "(" + addImp + ")";
		mainTargetFolderPath = mainTargetFolderPath + toAdd + "\\vulcantechimp@gmail.com\\Submissions\\Opt Out Ram";
		return mainTargetFolderPath;
	}
	
	public String[] getFilenames(String path) {
		//for each file in that list, read file as .txt
		//then get address out of that file as a String and put it in the optOutList
		File vulcantech = new File(path);
		String[] folderMembers = vulcantech.list();
		if (folderMembers.length == 0) {throw new IllegalArgumentException("The Opt Out folder appears to be empty.");}
		return folderMembers;
	}
	
	public ArrayList<String> readFile(String path){
		String line;
		ArrayList<String> fileAsList = new ArrayList<String>();
		try {
			
		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			while((line = br.readLine())!= null) {
				fileAsList.add(line);
			}
		}
		
		return fileAsList;
		}catch (java.io.IOException e) {e.printStackTrace(); return fileAsList;}	
	}
	
	public String trimOptOutEmail(String fullFile){
		//For maintenance, this starts by splitting at the phrase shown below. Then, it splits by ":", and finally it splits by "," to trim the end off the result.

		String[] splitter = fullFile.split("unsubscribed from the email list:, , ");
		
		String split1 = splitter[1];
		splitter = split1.split(":");
		String split2 = splitter[1];
		
		splitter = split2.split(",");
		split1 = splitter[0];
		split1.replace(",", "");

		return split1.trim();
	}
}//class