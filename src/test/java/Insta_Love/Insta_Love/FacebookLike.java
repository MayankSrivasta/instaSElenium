package Insta_Love.Insta_Love;

import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import library.browserConfiguration;

public class FacebookLike {
	public void highLight(ChromeDriver driver, WebElement element){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	public void smoothScroll(JavascriptExecutor js) {
		for(int i=0;i<=600;i=i+5)
			js.executeScript("window.scrollBy(0,5)");
	}
	public void getUserLink() {
		
	}
	@Test(priority=1)
	public void instaLogin() throws InterruptedException {
		ChromeDriver driver=browserConfiguration.openChrome("https://www.facebook.com/login/");
		Actions perform= new Actions(driver);
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 30);
		WebElement elm;
		List<WebElement> love,article;
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Log In")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("");;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys("");
		Thread.sleep(3000);
		driver.findElement(By.name("login")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class='_1vp5']")));
//Login complete here
		int limit=0;
		int no_occ=0;
		String style="";
		String pre_style="";
		Thread.sleep(3000);
		try {
			while(limit==0 || no_occ<3000 ) {
				love=driver.findElements(By.cssSelector("i[class='_6rk2 img sp_Gxq2MXbT8dg sx_1f64bb']"));// ("//span[@aria-label='Like']"));
				ListIterator<WebElement> i=love.listIterator();
				if(i.hasNext()) {
					limit=0;
					no_occ=0;
					while(i.hasNext()) {
						//smoothScroll(js);
						Thread.sleep(3000);
						//highLight(driver,i.next());
						perform.moveToElement(i.next()).build();
						//i.next().click();
						Thread.sleep(5000);
					}
				
				}
				else {
					limit=1;
					js.executeScript("window.scrollBy(0,1000)");
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class^='W1Bne']")));
					//Thread.sleep(200);
				}
				style=driver.findElement(By.cssSelector("div[style^='flex-direction: column; padding-bottom']")).getCssValue("padding-top");
				System.out.println("Pre style:"+pre_style+" /Style:"+style);
				if(style.equals(pre_style) && !style.equals("0px")) {
					no_occ++;
					System.out.println("Match found"+no_occ+" Times");
					Thread.sleep(10);
				}
				pre_style=style;
				love.clear();
			}
		}
		catch(Exception e) {
			System.out.println("Local exception:"+e);
			//driver.close();
		}
		//driver.close();				
	}
}
