package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_01_LoginTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
	}

	@Test
	public void TC_01_servicePortal() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.verifyServicePortal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}