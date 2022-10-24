package unsub;

public class XLSXStreamCrossException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XLSXStreamCrossException(){
		super("Resource Leak likely. FileOutputStream, FileInputStream, or Workbook stream was not closed successfully. Check affected files for corruption.");
	}

}
