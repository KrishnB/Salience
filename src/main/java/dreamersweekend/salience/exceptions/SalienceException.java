package dreamersweekend.salience.exceptions;

import java.io.FileNotFoundException;

import dreamersweekend.salience.commons.SalienceProperties;

public class SalienceException extends FileNotFoundException {

	private static final long serialVersionUID = 1L;
	private static SalienceProperties mSalieneceProp = new SalienceProperties();
	private final static String mBaseVersion = mSalieneceProp.getBaseVersion();
	private final static String mCurrentVersion = mSalieneceProp.getCurrentVersion();
	private final static String mVersionText = "Salience_Base_Version "+mBaseVersion+ " and Salience_Current_Version "+mCurrentVersion;
	
	public SalienceException() {
		super(mVersionText +" are not available for image comparison.");
	}
	
	public SalienceException(String message) {
		super(message);
	}
	

}
