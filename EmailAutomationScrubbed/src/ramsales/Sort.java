package ramsales;

import java.util.ArrayList;

import ramsales.Interfaces.Sortable;

public class Sort implements Sortable{

	ArrayList<String> masterOut = new ArrayList<String>();
	
	
	@Override
	public ArrayList<String> validateData(ArrayList<String> master, ArrayList<String> optout){
		
		for(String target : optout) {
			if(master.contains(target)) {master.remove(target);}
		}
		masterOut = master;
		return masterOut;	
	}
	
	
	
}//class block
