package TestCases;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_01_LoginTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("url"));
	}

	@Test
	public void TC_01_servicePortal() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.verifyServicePortal();
	}

//	@Test()
	public void TC_02_Random() {

		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		MyAssetsAndServicesPage myAssets = new MyAssetsAndServicesPage(driver);
		myAssets.clickOnMyAssets();
		loginpage.selectRandomPage();
		
		List<WebElement> assets = driver.findElements(By.xpath("//tbody//tr//td[1]//a"));

		Random r = new Random();
		int nextRandomNumberIndex = r.nextInt(assets.size());
		System.out.println(assets.get(nextRandomNumberIndex).getText());
	}
}