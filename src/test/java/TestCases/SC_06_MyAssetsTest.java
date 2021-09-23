package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.MyAssetsPage;
import Pages.RandomInputPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_06_MyAssetsTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
	}

	@Test(priority = 1)
	public void TC_01_verifySearch() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
			assetsandservices.clickOnMyAssets();
			MyAssetsPage myAsset = new MyAssetsPage(driver);
			myAsset.validSearchVerification();
			myAsset.invalidSearchVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void TC_02_sortingColumns() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
			assetsandservices.clickOnMyAssets();
			MyAssetsPage myAsset = new MyAssetsPage(driver);
			myAsset.sortingVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void TC_03_addAssetPageVerification() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
			assetsandservices.clickOnMyAssets();
			MyAssetsPage myAsset = new MyAssetsPage(driver);
			myAsset.clickOnAddAsset();
			myAsset.addAssetPageVerification(InitializePropertyFile.property.getProperty("SC_06_Page_Title"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void TC_04_blankColumnVerification() {

		try {
			RandomInputPage random = new RandomInputPage(driver);
			String randomUser = random.selectRandomUsername();
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
			assetsandservices.clickOnMyAssets();
			MyAssetsPage myAsset = new MyAssetsPage(driver);
			myAsset.blankColumnVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}