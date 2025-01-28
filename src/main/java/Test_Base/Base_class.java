package Test_Base;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;//log4j

public class Base_class {
	
	public WebDriver driver;
	public Logger logger;//log4j
	public Properties property;
	
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Master","Regression","Sanity","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String browser) throws IOException
	{
		FileReader readfile = new FileReader("./src//test//resources//config.properties");
		property = new Properties();
		property.load(readfile);
		logger = LogManager.getLogger(this.getClass());
		if(property.getProperty("Executiontype").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			switch (browser.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				 cap.setBrowserName("MicrosoftEdge");
				 break;
			default:
				System.out.println("Invalid browser name");
				return;
			}
			driver = new RemoteWebDriver(new URL(" http://localhost:4444/wd/hub"),cap);
		}
		if(property.getProperty("Executiontype").equalsIgnoreCase("local"))
		{
			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
			default:
				System.out.println("Browser name not matched");
				break;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(property.getProperty("url"));
		logger.info("********Chrome_browser_opened");
	}
	@AfterClass(groups = {"Master","Regression","Sanity","Datadriven"})
	public void Teardown()
	{
		driver.quit();
	}
	
	public String Randomnumber(int count)
	{
		String randomstring = RandomStringUtils.randomAlphabetic(count);
		return randomstring;
	}
	public String Randomnum(int count)
	{
		String randomnumber = RandomStringUtils.randomNumeric(count);
		return randomnumber;
	}
	public String Randomalphanumeric(int count)
	{
		String random_alphanumberic = RandomStringUtils.randomAlphanumeric(count);
		return random_alphanumberic;
	}
	public String Capturescreen(String tname)
	{
		String Timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
		String targetfilepath = System.getProperty("user.dir")+"\\Screenshots"+ tname+"_"+Timestamp+" .png";
		File Targetfile = new File(targetfilepath);
		
		sourcefile.renameTo(Targetfile);
		return targetfilepath;
		
	}

}
