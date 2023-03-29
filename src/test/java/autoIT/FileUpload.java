package autoIT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class FileUpload {
	WebDriver driver;
	
	@Test
	public void fileAutoIT() throws IOException, InterruptedException {
		String downloadPath = System.getProperty("user.dir");
		driver = initializeDriver(downloadPath);
		uploadFile(driver);
		downloadFile(driver,downloadPath);
		driver.close();
	}
	
	public void uploadFile(WebDriver driver) throws IOException, InterruptedException {
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		driver.findElement(By.cssSelector("a[id='pickfiles']")).click();
		
		Thread.sleep(2000);
		Runtime.getRuntime().exec("D:\\Selenium-Java Workspace\\Section30_AutoIT\\AutoIT.exe");
		Thread.sleep(3000);
	}
	
	public void downloadFile(WebDriver driver, String downloadPath) throws InterruptedException {
		driver.findElement(By.id("processTask")).click();
		
		File file = new File(downloadPath + "/ilovepdf_pages-to-jpg.zip");
		while(!file.exists()) {
			System.out.println("File Found");
			if(file.delete()) {
				System.out.println("File got deleted");
				break;
			}
		}
	}
	
	public WebDriver initializeDriver(String downloadPath) {
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		ops.setExperimentalOption("prefs", chromePrefs);
		
		WebDriver driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		return driver;
	}

}
