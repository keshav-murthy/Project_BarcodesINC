//package demo;
//
//import java.io.IOException;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import Pages.HomePage;
//import Pages.LoginPage;
//import Pages.MyAssetsAndServicesPage;
//import Pages.MyAssetsPage;
//import commons.InitializePropertyFile;
//import commons.TestBase;
//
//public class SC_04_VerifyFiltersTest extends TestBase {
//
//	@BeforeMethod
//	public void openPage() {
//		driver.get(InitializePropertyFile.property.getProperty("url"));
//	}
//
//	@Test
//	public void TC_04_VerifyTypeFilters() throws IOException, InterruptedException {
//
//		LoginPage loginpage = new LoginPage(driver);
//		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
//				InitializePropertyFile.property.getProperty("password"));
//		HomePage homepage = new HomePage(driver);
//		homepage.clickOnMyAssetsAndServices();
//		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
//		assetsandservices.clickOnMyAssets();
//		MyAssetsPage myassets = new MyAssetsPage(driver);
//		myassets.applyFilters();
//	}
//}