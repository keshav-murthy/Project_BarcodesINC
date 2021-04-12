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
		driver.get(InitializePropertyFile.property.getProperty("url"));

//		LoginPage loginpage = new LoginPage(driver);
//		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
//				InitializePropertyFile.property.getProperty("password"));
//		HomePage homepage = new HomePage(driver);
//		homepage.clickOnMyAssetsAndServices();
	}

//	@Test(priority = 1)
//	public void TC_01_verifySearch() {
//
//		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
//		assetsandservices.clickOnMyAssets();
//		MyAssetsPage myAsset = new MyAssetsPage(driver);
//		myAsset.verifyValidSearchedSerialNumber(InitializePropertyFile.property.getProperty("SC_06_Valid_Search"));
//		myAsset.verifyInvalidSearchedSerialNumber(InitializePropertyFile.property.getProperty("SC_06_Invalid_Search"));
//	}
//
//	@Test(priority = 2)
//	public void TC_02_sortingColumns() {
//
//		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
//		assetsandservices.clickOnMyAssets();
//		MyAssetsPage myAsset = new MyAssetsPage(driver);
//		myAsset.sortingVerification();
//	}
//
//	@Test(priority = 3)
//	public void TC_03_addAssetPageVerification() {
//
//		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
//		assetsandservices.clickOnMyAssets();
//		MyAssetsPage myAsset = new MyAssetsPage(driver);
//		myAsset.clickOnAddAsset();
//		myAsset.addAssetPageVerification(InitializePropertyFile.property.getProperty("SC_06_Page_Title"));
//	}

	@Test(priority = 4,invocationCount=1)
	public void TC_04_blankColumnVerification() {

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
	}
}