package seleniumGrid;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Demo1Test {
	
	@Test
	public void demo1() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
		caps.setPlatform(Platform.WINDOWS);
		
		WebDriver driver = new RemoteWebDriver(caps);
		driver.get("https://www.google.com");
		driver.getTitle();
		driver.close();
	}

}
