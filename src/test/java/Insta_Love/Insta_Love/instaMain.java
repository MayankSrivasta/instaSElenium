package Insta_Love.Insta_Love;
import java.util.List;
import java.util.ListIterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import library.browserConfiguration;
public class instaMain {
	public void highLight(ChromeDriver driver, WebElement element){
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	public void smoothScroll(JavascriptExecutor js) {
		for(int i=0;i<=400;i=i+5)
			js.executeScript("window.scrollBy(0,5)");
	}
	public void ScrollToElement(ChromeDriver driver,JavascriptExecutor js,WebElement elm) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elm);
		js.executeScript("window.scrollBy(0,-700)");
		elm.click();

	}
	public void getProfileId(ChromeDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//a[@href='/pallab_kr_mali/']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@title='pallab_kr_mali']")));
		driver.findElement(By.xpath("//a[@href='/pallab_kr_mali/following/']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/div/div[1]/h1/div")));
	}
	@Test(priority=1)
	public void instaLogin() throws InterruptedException {
		ChromeDriver driver=browserConfiguration.openChrome("https://www.instagram.com/accounts/login/?source=auth_switcher");
		WebDriver dr;
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver, 45);
		WebElement elm;
		List<WebElement> love;
		JavascriptExecutor js=(JavascriptExecutor)driver;
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/accounts/login/?source=auth_switcher']")));
		//driver.findElement(By.xpath("//a[@href='/accounts/login/?source=auth_switcher']")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Log in"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"react-root\"]/section/main/div/article/div/div[1]/div/form/div[3]")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username' and @type='text']")));
		//elm=driver.findElementByXPath("//input[@name='username' and @type='text']");
		//highLight(driver,elm);
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("pal5130@gmail.com");
		//driver.findElement(By.name("username")).sendKeys("pal5130@gmail.com");
		driver.findElement(By.name("password")).sendKeys("jaanmurkolijane"); //jaanmurkolija
		driver.findElementByXPath("//*[contains(text(), 'Log In')]").click(); //login button
		//driver.findElement(By.partialLinkText("Log In")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("pallab_kr_mali")));
		driver.findElement(By.xpath("//button[@class='aOOlW   HoLwm ']")).click(); //desktop notification not now
		//getProfileId(driver,wait);
		//Login complete here
		int limit=0;
		int no_occ=0;
		String style="";
		String pre_style="";
		String path="A";
		Thread.sleep(3000);
		try {
			while(limit==0 || no_occ<10000 ) {
				love=driver.findElements(By.cssSelector("span[class='glyphsSpriteHeart__outline__24__grey_9 u-__7'][aria-label='Like']"));// ("//span[@aria-label='Like']"));By.cssSelector("span[aria-label='Like']"))
				ListIterator<WebElement> i=love.listIterator();
				if(i.hasNext()) {
					limit=0;
					no_occ=0;
					while(i.hasNext()) {
						//smoothScroll(js);
						ScrollToElement(driver,js,i.next());
						Thread.sleep(1000);
						//highLight(driver,i.next());
						//path = i.next().getAttribute("xpath");
						//System.out.println("Path: "+path);
						//i.next().click();
						Thread.sleep(1000);
					}
				
				}
				else {
					limit=1;
					js.executeScript("window.scrollBy(0,600)");
					//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class^='W1Bne']")));
					Thread.sleep(100);
				}
				style=driver.findElement(By.cssSelector("div[style^='flex-direction: column; padding-bottom']")).getCssValue("padding-top");
				//System.out.println("Pre style:"+pre_style+" /Style:"+style);
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
			driver.close();
		}
		driver.close();
				
	}
}
