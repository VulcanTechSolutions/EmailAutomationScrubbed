package unsub;

import java.io.IOException;

public class SystoolsHandler {
	
	//script needs time to run, implementing a way for this program to wait based on completion is too tedious hence the "sleep" function used
	public void runSystoolsScript() {
		try {
			openSystools();
			
			Runtime rt = Runtime.getRuntime();
			rt.exec(new String[] {"cmd.exe", "/c cd C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\Outside-Script & task-UAC-AutoIT.bat"});
			sleepThread(35);
			System.out.println("done");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void openSystools() throws IOException {
		Runtime rt = Runtime.getRuntime();
		rt.exec(new String[] {"cmd.exe", "/c cd C:\\Users\\SCRUBBED\\Desktop\\Tools Proper Temp\\Official Projects\\EmailAutomation\\Outside-Script & task-UAC-Systools.bat"});
		sleepThread(7);
	}

	public void sleepThread(int seconds){
	    try {
	        Thread.sleep(seconds*1000);
	        }catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
}//class