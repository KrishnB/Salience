package dreamersweekend.salience.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author 416474
 *
 */
public final class SalienceProperties{
	float mBaseVersion,mCurrentVersion,mIncompatibleVersion;
	Properties mSalienceProperties = new Properties();
	Properties mScenarioProperties = new Properties();
	FileInputStream mFinSalienceProp,mFinScenarioProp;
	FileOutputStream mFout;

	public SalienceProperties() {
		try {
			mFinSalienceProp = new FileInputStream("Salience.properties");
			mFinScenarioProp = new FileInputStream("Scenarios.properties");
			mSalienceProperties.load(mFinSalienceProp);
			mScenarioProperties.load(mFinScenarioProp);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBaseVersion(String baseVersion){
		try {
			mFout = new FileOutputStream("Salience.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mSalienceProperties.setProperty("Salience_Base_Version", baseVersion);
		try {
			mSalienceProperties.store(mFout, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getBaseVersion(){

		return mSalienceProperties.getProperty("Salience_Base_Version");

	}

	public void setCurrentVersion(String currentVersion){
		try {
			mFout = new FileOutputStream("Salience.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mSalienceProperties.setProperty("Salience_Current_Version", currentVersion);
		try {
			mSalienceProperties.store(mFout, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCurrentVersion(){

		return mSalienceProperties.getProperty("Salience_Current_Version");
	}

	public void setIncompatibleVersion(String incompatibleVersion){
		try {
			mFinSalienceProp.close();
			mFout = new FileOutputStream("Salience.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mSalienceProperties.setProperty("Salience_Incompatible_Version", incompatibleVersion);
		try {
			mSalienceProperties.store(mFout, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setScenarioName(String scenarioName, int scenarioNum){
		try {
			mFout = new FileOutputStream("Scenarios.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mScenarioProperties.setProperty("Scenario"+scenarioNum, scenarioName);
		try {
			mScenarioProperties.store(mFout, null);
			mFout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRunType(){

		return mSalienceProperties.getProperty("ImageType");
	}

	public String getScenarioName(String scenario){

		return mScenarioProperties.getProperty(scenario);
	}

	@Deprecated
	public boolean compareImages(){
		if(mSalienceProperties.getProperty("Image_Comparison").toUpperCase().equals("YES")){
			return true;
		}else if(mSalienceProperties.getProperty("Image_Comparison").toUpperCase().equals("NO")){
			return false;
		}else{
			throw new IllegalArgumentException("Define Image_Comparison property in 'Salience.properties' file as either Yes or No only");
		}
	}

	public String getIncompatibleVersion(){

		return mSalienceProperties.getProperty("Salience_Incompatible_Version");
	}

	public String getBaseURL(){

		return mSalienceProperties.getProperty("Base_Url");
	}

	public String getCurrentURL(){

		return mSalienceProperties.getProperty("Current_Url");
	}

	public boolean isJavaScriptDisabled() {
		if(mSalienceProperties.getProperty("Disable_Javascript").equalsIgnoreCase("true")) {
			return false;
		}else{
			return true;
		}
	}
	
	public String getProxySetStatus() {
		return mSalienceProperties.getProperty("ProxySet");
	}
	
	public String getProxyHost() {
		return mSalienceProperties.getProperty("ProxyHost");
	}
	
	public String getProxyPort() {
		return mSalienceProperties.getProperty("ProxyPort");
	}
	
	public String getBrandName() {
		return mSalienceProperties.getProperty("BrandName");
	}
	
	public String getBrandName1() {
		return mSalienceProperties.getProperty("BrandName1");
	}
	
	public String getLocale(){
		return mSalienceProperties.getProperty("Locale");
	}
	
	public String getDefaultBrowser(){
		return mSalienceProperties.getProperty("DefaultBrowser");
	}
	
	public String getChromeDriverPath(){
		return mSalienceProperties.getProperty("ChromeDriver");
	}
	
	public String getIEDriverPath(){
		return mSalienceProperties.getProperty("IEDriver");
	}
	
	public String getProxyUser(){
		return mSalienceProperties.getProperty("ProxyUser");
	}
	
	public String getProxyPassword(){
		return mSalienceProperties.getProperty("ProxyPassword");
	}

	public void setFilename(String filename){
		try {
			mFout = new FileOutputStream("Scenarios.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mScenarioProperties.setProperty("Filename_"+filename, filename+".ser");
		try {
			mScenarioProperties.store(mFout, null);
			mFout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileName(String filename) {
		return mScenarioProperties.getProperty("Filename_"+filename);
	}
	
	public String getImageFormat(){
		return mSalienceProperties.getProperty("Image_Format");
	}
	
	public String getIncludeAllLinks(){
		return mSalienceProperties.getProperty("Include_All_Links");
	}
	
	public String getResultsFolderName(){
		return mSalienceProperties.getProperty("Foldername");
	}
	
	public String getWorkbookname() {
		return mSalienceProperties.getProperty("Workbookname");
	}
	
	public String getWorksheetname() {
		return mSalienceProperties.getProperty("Worksheetname");
	}
	
	public String getImageComparisonType() {
		return mSalienceProperties.getProperty("Image_Comparsion_Type");
	}
	
	public String getColor() {
		return mSalienceProperties.getProperty("Color");
	}
	
	public String getLocale1() {
		return mSalienceProperties.getProperty("Locale1");
	}
}
