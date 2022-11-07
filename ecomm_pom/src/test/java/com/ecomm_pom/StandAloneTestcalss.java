package com.ecomm_pom;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.security.*; 
import java.math.*;

import com.testcomponents.BaseTest;
import com.testcomponents.PasswordEncoder;

import ecomm_pom.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestcalss extends BaseTest{
	
	@Test
	public  void standalone () throws InterruptedException, IOException {
		String user = "sachin.kulkarni164@gmail.com";
		String pwd1;
		BaseTest bt = new BaseTest();
		driver = bt.Initialize();
		PasswordEncoder pw = new PasswordEncoder();
		byte[] val = pw.PasswordEncode();
		byte[] decodeString = Base64.decodeBase64(val);
		pwd1 = new String(decodeString);
		System.out.println(pwd1);
		driver.get("https://www.amazon.in/");
		LandingPage lp = new LandingPage(driver);
		lp.login(user, pwd1);
		lp.getScreenshot("standalone1", driver);
		lp.search_order();
		
	}

}
