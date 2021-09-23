package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LocationsPage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_13_AdminPanelTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
	}

	@Test(priority = 1)
	public void TC_01_verifyLocationDetailPage() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myassets = new MyAssetsAndServicesPage(driver);
			myassets.clickOnLocation();
			LocationsPage location = new LocationsPage(driver);
			location.clickOnShowUser();
			location.verifyLocationDetailPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void TC_02_verifySearchfield() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myassets = new MyAssetsAndServicesPage(driver);
			myassets.clickOnLocation();
			LocationsPage location = new LocationsPage(driver);
			location.liveSearchVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void TC_03_verifyAssetTab() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myassets = new MyAssetsAndServicesPage(driver);
			myassets.clickOnLocation();
			LocationsPage location = new LocationsPage(driver);
			location.selectUser();
			location.verifyAssetsTab();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}