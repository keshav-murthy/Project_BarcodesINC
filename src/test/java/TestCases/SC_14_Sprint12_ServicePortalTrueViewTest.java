package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.ReportDashboardPage;
import Pages.ViewReportPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_14_Sprint12_ServicePortalTrueViewTest extends TestBase {

	@BeforeMethod
	public void openPage() {
		driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
	}

	@Test(priority = 1)
	public void TC_01_AssetPercentageQuantityValidation() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			dashboard.clickOnSupportContractReport();
			ViewReportPage report = new ViewReportPage(driver);
			int totalTSPcontracts = report.getTotalcontracts();
			driver.navigate().back();
			dashboard.verifyAssetsDisplayForTSP(totalTSPcontracts);

			dashboard.clickOnOEMContractReport();
			int totalOEMcontracts = report.getTotalcontracts();
			driver.navigate().back();
			dashboard.verifyAssetsDisplayForOEM(totalOEMcontracts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void TC_02_TrueViewLinkValidation() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.verifyTrueViewContents();
			HomePage.javaScriptClick(homepage.createTicket);
			homepage.verifyLink("Create Ticket");
			HomePage.javaScriptClick(homepage.myAssets);
			homepage.verifyLink("My Assets");
			HomePage.javaScriptClick(homepage.myContracts);
			homepage.verifyLink("My Contracts");
			HomePage.javaScriptClick(homepage.myTickets);
			homepage.verifyLink("My Tickets");
			homepage.verifyTrueViewStyle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void TC_03_OEMContractValidation() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			dashboard.clickOnOEMContractReport();
			ViewReportPage report = new ViewReportPage(driver);
			report.verifyBreadcrumbTrail();
			report.verifyContractColumn();
			report.verifyLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}