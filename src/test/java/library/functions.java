package library;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class functions {
	public void highLight(ChromeDriver driver, WebElement element) throws InterruptedException{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'background: blue; border: 2px solid red;');", element);
		 }
	
}
