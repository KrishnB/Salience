package sampleTests;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeSSTest {

	@Test
	public void test() throws InterruptedException, AWTException, IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\WebDrivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.flipkart.com");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("return document.body.style.zoom=\"30%\"");
		File  imFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(imFile, new File("Test.png"));
	}

}
