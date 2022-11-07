package ecomm_pom.pageobjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[@id='nav-link-accountList']")
	WebElement userSignIn;
	
	@FindBy(xpath="//input[@id='ap_email']")
	WebElement user;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement countinuebtn;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement pwd;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	WebElement signinbtn;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchbox;
	
	@FindBy(xpath="//span[@class='a-size-medium a-color-base a-text-normal'][contains(text(),'Samsung Galaxy M32 Prime Edition (Black, 4GB RAM, ')]")
	WebElement samsungm32;
	
	@FindBy(xpath="//input[@id='buy-now-button']")
	WebElement buybtn;
	
	@FindBy(xpath="//span[contains(text(),'sachin')]//ancestor::span[2]//ancestor::label//child::input")
	WebElement radiobtn;
	
	@FindBy(xpath="//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']")
	WebElement usethisaddrbtn;
	
	@FindBy(xpath="//span[contains(text(),'Cash On Delivery')]//ancestor::span[2]//ancestor::label//child::input")
	WebElement cod_radiobtn;
	
	@FindBy(xpath="//span[@class='a-button-inner']//input[@name='ppw-widgetEvent:SetPaymentPlanSelectContinueEvent']")
	WebElement use_this_payment_btn;
	
	@FindBy(xpath="//span[@id='submitOrderButtonId']//input")
	WebElement placeorderbtn;
	
	public void login(String email, String Password) throws InterruptedException, IOException {
		userSignIn.click();
		user.sendKeys(email);
		countinuebtn.click();
		pwd.sendKeys(Password);
		getScreenshot("SignIn",driver);
		signinbtn.click();
		
		//using to bypass 2fa in amazon
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[text()='Hello, sachin']")));	
		
	}
	public void search_order() throws InterruptedException, IOException {
		searchbox.click();
		searchbox.sendKeys("Samsung Galaxy M32 Prime Edition (Black, 4GB RAM, 64GB)",Keys.ENTER);
		//searchbox.sendKeys(Keys.ENTER);
		samsungm32.click();
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTb.get(1));
		Thread.sleep(3000);
		buybtn.click();
		radiobtn.click();
		usethisaddrbtn.click();
		cod_radiobtn.click();
		getScreenshot("order_page",driver);
		use_this_payment_btn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@aria-labelledby='submitOrderButtonId-announce']")));
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].click();", placeorderbtn);
		
		/*Actions builder = new Actions(driver);
	     builder.moveToElement(placeorderbtn).click(placeorderbtn);
	     builder.perform();
		Thread.sleep(5000);*/
		driver.switchTo().window(newTb.get(0));
		driver.quit();
	}
	
	public void getScreenshot(String testcasename,WebDriver driver) throws IOException {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//screenshot//"+ testcasename + ".png");
		FileUtils.copyFile(SrcFile, file);
	}
}
