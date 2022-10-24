package unsub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CounterHandler {

	private String counterFilePath;
	public void setPath(String path) {
		this.counterFilePath = path;
	}
		
	public int readCounterFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(counterFilePath)));
			String countImp = br.readLine();
			br.close();
			int returnValue = Integer.parseInt(countImp.trim());
			return returnValue;
		} catch (IOException e) {
			System.out.println("Either Counter File doesn't exist or Induction is broken");
			e.printStackTrace();
			int whoops = -100;
			return whoops;
		}
	}
	
	public void updateCounterFile (int currentCount) {
		
		try {
			BufferedWriter bw = Files.newBufferedWriter(Paths.get(counterFilePath));
			currentCount++;
			int valueToWrite = currentCount;
			String yTho = "" + valueToWrite;
			//System.out.println(yTho);
			
			bw.write(yTho);
			bw.close();
			
		}catch (IOException e) {System.out.println("updating the counter file didn't work for some reason.");}	
	}	
}