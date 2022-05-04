package net.petrikainulainen.junit5;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class Example2Test {

	private WebDriver driver;
	private Map<String, Object> vars;
	String driverPath = "/Users/anshitkumarsharma/Desktop/selenium-ammu/chromedriver";
	String myActualIP = "2405:201:800c:1a03:a4c4:e6ad:3121:7650";

	@BeforeEach

	void setUp() throws Exception {

		System.err.println("running!! setup");

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		vars = new HashMap<String, Object>();

	}

	@AfterEach
	void tearDown() throws Exception {

		System.err.println("running!! teardown");

		driver.quit();
	}

	@Test
	void test() {

		System.err.println("running!! test");

		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys("what is my ip");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.textToBe(By.cssSelector(".a9v0ne"), "What\'s my IP"));
		}
		vars.put("text", driver.findElement(By.cssSelector(".NEM4H:nth-child(1) > span > span")).getText());
		String myIP = vars.get("text").toString();
		Assert.isTrue(myIP.contentEquals(myIP), "Matches", new Object[] { myIP, myActualIP });

	}

}
