package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyAssetsAndServicesPage;
import Pages.RandomInputPage;
import Pages.ReportDashboardPage;
import Pages.ViewReportPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_02_DashboardTest extends TestBase {

	@Parameters({"URL"})
	@BeforeMethod
	public void openPage(String URL) {
		if(URL.equals("Prod"))
		driver.get(InitializePropertyFile.property.getProperty("BarcodesINC_URL"));
		else if(URL.equals("Sandbox"))
			driver.get(InitializePropertyFile.property.getProperty("Sandbox_URL"));
		else if(URL.equals("Staging"))
			driver.get(InitializePropertyFile.property.getProperty("Staging_URL"));
		System.out.println(driver.getCurrentUrl());
	}

	@Test(priority = 1)
	public void TC_01_dashboardWidgets() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			dashboard.verifyWidgetsInDashboard();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void TC_02_viewReport() {

//		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			String widgetTitle = dashboard.selectRandomWidget();
			dashboard.clickOnViewReport(widgetTitle);
			ViewReportPage report = new ViewReportPage(driver);
			report.verifyReportDetails(widgetTitle);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test(priority = 3)
	public void TC_03_verifySearch() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			dashboard.clickOnViewReport(InitializePropertyFile.property.getProperty("Widget_Title"));
			ViewReportPage report = new ViewReportPage(driver);
			report.validSearchVerification();
			report.invalidSearchVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void TC_04_verifyPagination() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			String widgetTitle = dashboard.selectRandomWidget();
			dashboard.clickOnViewReport(widgetTitle);
			ViewReportPage report = new ViewReportPage(driver);
			report.paginationVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5)
	public void TC_05_sortingColumns() {

		try {
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			dashboard.clickOnViewReport(InitializePropertyFile.property.getProperty("Widget_Title"));
			ViewReportPage report = new ViewReportPage(driver);
			report.sortingVerification();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	public void TC_06_dateRange() {

		try {
			String url=driver.getCurrentUrl();
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(InitializePropertyFile.property.getProperty("username"),
					InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			dashboard.clickOnViewReport(InitializePropertyFile.property.getProperty("Widget_Title"));
			ViewReportPage report = new ViewReportPage(driver);
			report.dateRangeVerification(InitializePropertyFile.property.getProperty("SC_02_From_Date"),
					InitializePropertyFile.property.getProperty("SC_02_To_Date"),url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 7)
	public void TC_07_repairIDVerification() {

		try {
			RandomInputPage random = new RandomInputPage(driver);
			String randomUser = random.selectRandomUsername();
			LoginPage loginpage = new LoginPage(driver);
			loginpage.userLoginProcedure(randomUser, InitializePropertyFile.property.getProperty("password"));
			HomePage homepage = new HomePage(driver);
			homepage.clickOnMyAssetsAndServices();
			MyAssetsAndServicesPage myAssets=new MyAssetsAndServicesPage(driver);
			myAssets.clickOnReportDashboard();
			ReportDashboardPage dashboard = new ReportDashboardPage(driver);
			String widgetTitle = dashboard.selectRandomTicketWidget();
			dashboard.clickOnViewReport(widgetTitle);
			ViewReportPage report = new ViewReportPage(driver);
			report.blankRepairIDVerification(widgetTitle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}