//package demo;
//
//import java.io.IOException;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import Pages.AddContractPage;
//import Pages.HomePage;
//import Pages.LoginPage;
//import Pages.MyAssetsAndServicesPage;
//import Pages.MyAssetsPage;
//import Pages.RandomInputPage;
//import commons.InitializePropertyFile;
//import commons.TestBase;
//
//public class SC_02_AddContractTest extends TestBase {
//
//	@BeforeMethod
//	public void openPage() {
//		driver.get(InitializePropertyFile.property.getProperty("url"));
//	}
//
//	@Test
//	public void TC_02_AddContract() throws IOException, InterruptedException {
//
//		LoginPage loginpage = new LoginPage(driver);
//		loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
//				InitializePropertyFile.property.getProperty("password"));
//		HomePage homepage = new HomePage(driver);
//		homepage.clickOnMyAssetsAndServices();
//		MyAssetsAndServicesPage assetsandservices = new MyAssetsAndServicesPage(driver);
//		assetsandservices.clickOnMyAssets();
//		MyAssetsPage myassets = new MyAssetsPage(driver);
//		myassets.clickOnAddContract();
//		RandomInputPage randomInput = new RandomInputPage(driver);
//		String random = randomInput.selectRandomInput();
//		AddContractPage addcontract = new AddContractPage(driver);
//		addcontract.addingContractProcedure(random);
//	}
//}