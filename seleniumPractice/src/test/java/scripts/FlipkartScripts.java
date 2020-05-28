package scripts;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.GetSetProperties;
import utilities.HeadlessBrowser;

public class FlipkartScripts {
	WebDriver driver;
	WebDriverWait wait;
	GetSetProperties prop = new GetSetProperties();

	@Test
	public void closeHomePagePopUp() throws InterruptedException {
		driver.get("http://www.flipkart.com");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[contains(@class,'_2AkmmA _29YdH8')]")));
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//button[contains(@class,'_2AkmmA _29YdH8')]")).click();
		// driver.findElement(By.xpath("//*[text()[contains(.,'_2AkmmA
		// _29YdH8')]]")).click();
		// driver.findElement(By.xpath("//input[@class='_2zrpKA
		// _1dBPDZ']")).sendKeys("abcd");
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//button[contains(@class,'_2AkmmA _29YdH8')]")));
	}

	@Test
	public void homePageMenuList() throws InterruptedException {
		driver.get("http://www.flipkart.com");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[contains(@class,'_2AkmmA _29YdH8')]")));
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//button[contains(@class,'_2AkmmA _29YdH8')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='_114Zhd']/child::li")));
		List<WebElement> menuList = driver.findElements(By.xpath("//ul[@class='_114Zhd']/child::li"));
		System.out.println(menuList.size());
		List<String> actualMenuList = new ArrayList<String>();
		for (int i = 0; i < menuList.size(); i++) {
			System.out.println(menuList.get(i).getText());
			actualMenuList.add(i, menuList.get(i).getText());
		}
		List<String> expectedMenuList = new ArrayList<String>();
		expectedMenuList.add("Electronics");
		expectedMenuList.add("TVs & Appliances");
		expectedMenuList.add("Men");
		expectedMenuList.add("Women");
		expectedMenuList.add("Baby & Kids");
		expectedMenuList.add("Home & Furniture");
		expectedMenuList.add("Sports, Books & More");
		expectedMenuList.add("Flights");
		expectedMenuList.add("Offer Zone");
		for (int i = 0; i < expectedMenuList.size(); i++) {
			System.out.println(expectedMenuList.get(i));
		}
		prop.setProperties("result", "pass2");
		Assert.assertEquals(actualMenuList, expectedMenuList);

	}

	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException {
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\SANJEEV
		// SINGH\\eclipse-workspace\\seleniumPractice\\src\\test\\resources\\drivers\\chromedriver.exe");

		if (prop.getProperties("browser").equals("chrome")) {
			HeadlessBrowser.runInHeadlessMode();
			WebDriverManager.chromedriver().setup();
			System.out.println(prop.getProperties("browser"));
			driver = new ChromeDriver(HeadlessBrowser.getChromeOption());
		}

		/*
		 * WebDriverManager.firefoxdriver().setup();
		 * WebDriverManager.edgedriver().setup();
		 * WebDriverManager.operadriver().setup(); WebDriverManager.phantomjs().setup();
		 * WebDriverManager.iedriver().setup();
		 * WebDriverManager.chromiumdriver().setup();
		 */

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
