package dreamersweekend.salience.stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dreamersweekend.salience.commons.BrowserTypes;
import dreamersweekend.salience.commons.Commons;
import dreamersweekend.salience.commons.ConcurrentRunner;
import dreamersweekend.salience.commons.Directories;
import dreamersweekend.salience.commons.ImageComparisonType;
import dreamersweekend.salience.commons.RunType;
import dreamersweekend.salience.commons.SalienceProperties;
import dreamersweekend.salience.commons.Utils;
import dreamersweekend.salience.core.Salience;
import dreamersweekend.salience.core.URLExtractor;
import dreamersweekend.salience.driver.BrowserDriver;
import dreamersweekend.salience.exceptions.SalienceException;
import dreamersweekend.salience.reporting.Reporter;
import dreamersweekend.salience.serialized.ImagesState;
import dreamersweekend.salience.serialized.ReadObject;
import dreamersweekend.salience.serialized.WriteObject;

/**
 * @author 416474
 *
 */

public final class SalienceStepDefinitions{
	
	static WebDriver sDriver;
	BrowserDriver mBrowserDriver;
	BrowserDriver[] mBrowserDrivers;
	static String sScenarioName,sBrowserName;
	static String[] sBrowserNames[];
	static int sScenarioNum =0;
	SalienceProperties mSalienceProperties = new SalienceProperties();
	Directories mDirectories;
	File mDocFile;
	String sScenario;
	List<File> mImgFiles = new ArrayList<File>();
	ImagesState mImgsState = new ImagesState();
	private String mBaseUrl;
	private HashMap<String, String> mUrlsMap;
	private static String sFilename;

	//@Before
	public void getScenarioName(Scenario scenario) {
		sScenarioNum++;
		String tempStr = scenario.getId();
		System.out.println(tempStr);
		sScenarioName = tempStr.substring(tempStr.indexOf(';')+1, tempStr.length());
		mSalienceProperties.setScenarioName(sScenarioName.replace('-', '_'), sScenarioNum);
	}

	//@Before 
	public void createDirectories() {
		mDirectories = new Directories();
		String scenarioName = getScenarioName();
		mDirectories.createBaseScenarioDirectory(scenarioName);
		mDirectories.createCurrentScenarioDirectory(scenarioName);
		mDirectories.createIncompatibleScenarioDirectory(scenarioName);
	}
	
	@Before 
	public void createDirectories(Scenario scenario) {
		mDirectories = new Directories();
		sScenarioNum++;
		String tempStr = scenario.getId();
		System.out.println(tempStr);
		sScenarioName = tempStr.substring(tempStr.indexOf(';')+1, tempStr.length());
		mSalienceProperties.setScenarioName(sScenarioName.replace('-', '_'), sScenarioNum);
		String scenarioName = getScenarioName();
		mDirectories.createBaseScenarioDirectory(scenarioName);
		mDirectories.createCurrentScenarioDirectory(scenarioName);
		mDirectories.createIncompatibleScenarioDirectory(scenarioName);
	}

	@Given("^I open a web browser through \"([^\"]*)\"$")
	public void I_open_a_web_browser_through(String arg1) throws Throwable {
		if(isNotIgnored()){
			String browser = null;
			if((arg1.equals(""))){
				browser = mSalienceProperties.getDefaultBrowser();
			}else {
				browser = arg1;
			}
			boolean disableJavascript = mSalienceProperties.isJavaScriptDisabled();
			BrowserTypes browserType = Utils.getBrowserType(browser);
			sBrowserName = Utils.getBrowserName(browser);
			mBrowserDriver = new BrowserDriver(browserType);
			mBrowserDriver.initDriver(disableJavascript);
		}
	}

	@Given("^I open a web browser through \"(.*?)\",\"(.*?)\"$")
	public void i_open_a_web_browser_through(String arg1, String arg2) throws Throwable {
		if(isNotIgnored()){
			String[] browsers = {arg1,arg2};
			ConcurrentRunner runner = new ConcurrentRunner(browsers);
			runner.execute();
		}

	}

	@When("^I enter the url \"([^\"]*)\"$")
	public void I_enter_the_url(String arg1) throws Throwable {
		if(isNotIgnored()){
			if(arg1.equals("") && mSalienceProperties.getRunType().equals("Base_Image")){
				mBaseUrl = mSalienceProperties.getBaseURL();
			}else if(arg1.equals("") && mSalienceProperties.getRunType().equals("Current_Image")){
				mBaseUrl = mSalienceProperties.getCurrentURL();
			}else{
				mBaseUrl = arg1;
			}
			mBrowserDriver = new BrowserDriver();
			mBrowserDriver = mBrowserDriver.getBrowserDriverInstance();
		}

	}

	@Deprecated
	@Then("^I should be navigated to the \"([^\"]*)\" page.$")
	public void I_should_be_navigated_to_the_page(String arg1) throws Throwable {	
		boolean isValidPage;
		String title = mBrowserDriver.getTitle();

		if(arg1.equals(title)){
			isValidPage = true;
		}else{
			isValidPage = false;
		}
		Assert.assertTrue(isValidPage);

	}
	
	@Deprecated
	@Then("^I take the screenshot as \"(.*?)\"$")
	public void i_take_the_screenshot_as(String imageName) throws Throwable {
		Thread.sleep(5000);
		sScenario = mSalienceProperties.getScenarioName("Scenario"+sScenarioNum);
		mDocFile = new File(imageName.replace(" ", "_")+"_"+sBrowserName);
		mImgFiles.add(mDocFile);
		mBrowserDriver.captureDocument(sScenario, mDocFile);
	}

	@Then("^I quit$")
	public void i_quit() throws Throwable {
		if(isNotIgnored()){
			mBrowserDriver.quitBrowser();
		}
	}

	@Then("^I perform Image Comparison")
	public void i_perform_image_comparison() throws InterruptedException {
		try {
		
		if(isNotIgnored()){
			URLExtractor urlExtractor = new URLExtractor(mBaseUrl);
			urlExtractor.extractURLs(mImgsState);
			mUrlsMap = mImgsState.getUrlsMap();
			System.out.println(mUrlsMap.size()-1);
			Set<String> keyUrls = mUrlsMap.keySet();
			Iterator<String> keyIterator = keyUrls.iterator();
			while (keyIterator.hasNext()) {
				Object keyUrl = keyIterator.next();
				Object keyText = mUrlsMap.get(keyUrl);	
				System.out.println("The name is "+keyText+"="+keyUrl);
				Object parsedKeyUrl = parseUrl(keyUrl);
				mBrowserDriver.getURL(parsedKeyUrl);
				mBrowserDriver.waitTillPageGetsLoaded();
				Thread.sleep(3000);
				if(!(Commons.isPageBroken(mBrowserDriver.getTitle()))){
					takeScreenShotAs(keyText);
				}
			}
			mBrowserDriver.quitBrowser();
		}	
		
		} catch (Exception e) {
			// will be handled by @After
		}
	}

	private void takeScreenShotAs(Object keyText) {
		if(keyText != null) 
		try {
			Thread.sleep(5000);
			String scenarioName = getScenarioName();
			mDocFile = new File(((String) keyText).replace(" ", "_")+"_"+sBrowserName);
			mImgsState.addtoImageFiles(mDocFile);
			mBrowserDriver.captureDocument(scenarioName, mDocFile);
		} catch (InterruptedException e) {

		}
		
	}

	@After
	public void compareImages() throws IllegalAccessException {
		if(!isNotIgnored()){
			for(File images: imgFiles()){
				String scenarioName = getScenarioName();
				ImageComparisonType imgCompType = Commons.getImageComparisonType();
				Salience imDiff = new Salience(images, scenarioName,imgCompType);
				try {
					imDiff.writeIncompatibleImage();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Error e) {
					e.printStackTrace();
				}
			}
			String date = Commons.getCurrentDate();
			String brandName = mSalienceProperties.getBrandName();
			String folderName = brandName+"_"+date;
			String workbookname = Commons.getWorkbookName();
			String worksheetname = Commons.getWorksheetName();
			Reporter report = new Reporter(folderName, workbookname, worksheetname, sFilename);
			report.createReportFolder();
			report.createWorkbook();
			report.createSheet();
			report.developReport();
			report.writeWorkbook();
		} else {
			saveImageFiles();
			mImgFiles.clear();
			mImgsState.clearUrlsMap();
			if (mBrowserDriver!=null) {
				mBrowserDriver.quitBrowser();
			}
		}
	}

	private String getScenarioName() {
		String scenario = mSalienceProperties.getScenarioName("Scenario"+sScenarioNum);
		String brandName = mSalienceProperties.getBrandName();
		String locale = mSalienceProperties.getLocale();
		StringBuilder builder = new StringBuilder();
		StringBuilder str = builder.append(scenario+"_"+brandName+"_"+locale);
		String scenarioName = str.toString();
		return scenarioName;
	}

	private List<File> imgFiles(){
		setFileName();	
		ReadObject<ImagesState> objState = new ReadObject<>(Commons.getRunType());
		ImagesState imgState = null;
		try {
			imgState = objState.readObjectState(sFilename);
		} catch (SalienceException e) {
				try {
					throw new SalienceException();
				} catch (SalienceException e1) {
					e1.printStackTrace();
				}
			System.exit(1);
		}
		return imgState.getImageFiles();
	}

	private void saveImageFiles() {
		setFileName();
		mSalienceProperties.setFilename(sFilename);
		WriteObject<ImagesState> writeImagesState = new WriteObject<ImagesState>(mImgsState,Commons.getRunType() );
		writeImagesState.writeObjectState(sFilename); //Saving object state
	}

	private Object parseUrl(Object keyUrl) {
		String username = mSalienceProperties.getProxyUser();
		String password = mSalienceProperties.getProxyPassword();
		Object parsedUrl = null;
		if (username.length()>0 || password.length()>0) {
			String[] urlSplitter = ((String) keyUrl).split("//");
			parsedUrl = urlSplitter[0]+"//"+username+":"+password+"@"+urlSplitter[1];
		}else {
			parsedUrl = keyUrl;
		}
		
		return parsedUrl;
	}

	private void setFileName() {
		String brandname = mSalienceProperties.getBrandName();
		String locale = mSalienceProperties.getLocale();
		if(Commons.getRunType().equals(RunType.BASE)) {
			sFilename = brandname+mSalienceProperties.getBaseVersion()+"_"+locale;
		}else if(Commons.getRunType().equals(RunType.CURRENT)) {
			sFilename = brandname+mSalienceProperties.getCurrentVersion()+"_"+locale;
		}	
	}

	private Boolean isNotIgnored() {
		if(mSalienceProperties.getRunType().contains("Incompatible")){			
			return false;
		}else {
			return true;
		}
	}
	
	public static String getBrowserName() {
		
		return sBrowserName;
	}
}
