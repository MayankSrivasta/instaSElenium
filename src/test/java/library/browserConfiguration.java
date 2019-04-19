package library;

import org.openqa.selenium.chrome.ChromeDriver;

public class browserConfiguration {
	public static ChromeDriver openChrome(String url) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pallab\\Documents\\PlugIns\\chromedriver_win32\\chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.get(url);
		return driver;
	}

}
