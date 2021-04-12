package TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.RandomInputPage;
import Pages.ReportDashboardPage;
import Pages.ViewReportPage;
import commons.InitializePropertyFile;
import commons.TestBase;

public class SC_02_DashboardTest extends TestBase {

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
//	public void TC_01_dashboardWidgets() {
//
//		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
//		dashboard.verifyWidgetsInDashboard();
//	}
//
//	@Test(priority = 2)
//	public void TC_02_viewReport() {
//
//		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
//		String widgetTitle = dashboard.selectRandomWidgetWithFilter();
//		dashboard.clickOnViewReport(widgetTitle);
//		ViewReportPage report = new ViewReportPage(driver);
//		report.verifyReportDetails(widgetTitle);
//	}
//
//	@Test(priority = 3)
//	public void TC_03_verifySearch() {
//
//		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
//		dashboard.clickOnViewReport(InitializePropertyFile.property.getProperty("Widget_Title"));
//		ViewReportPage report = new ViewReportPage(driver);
//		report.validSearchVerification(InitializePropertyFile.property.getProperty("SC_02_Valid_Search"));
//		report.invalidSearchVerification(InitializePropertyFile.property.getProperty("SC_02_Invalid_Search"));
//	}
//
//	@Test(priority = 4)
//	public void TC_04_verifyPagination() {
//
//		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
//		String widgetTitle = dashboard.selectRandomWidget();
//		dashboard.clickOnViewReport(widgetTitle);
//		ViewReportPage report = new ViewReportPage(driver);
//		report.paginationVerification();
//	}
//
//	@Test(priority = 5)
//	public void TC_05_sortingColumns() {
//
//		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
//		String widgetTitle = dashboard.selectRandomWidget();
//		dashboard.clickOnViewReport(widgetTitle);
//		ViewReportPage report = new ViewReportPage(driver);
//		report.sortingVerification();
//	}
//
//	@Test(priority = 6)
//	public void TC_06_dateRange() {
//
//		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
//		dashboard.clickOnViewReport(InitializePropertyFile.property.getProperty("Widget_Title"));
//		ViewReportPage report = new ViewReportPage(driver);
//		report.dateRangeVerification(InitializePropertyFile.property.getProperty("SC_02_From_Date"),
//				InitializePropertyFile.property.getProperty("SC_02_To_Date"));
//	}
	
	@Test(priority = 7,invocationCount=3)
	public void TC_07_repairIDVerification() {
		
		RandomInputPage random=new RandomInputPage(driver);
		String randomUser=random.selectRandomUsername();
		LoginPage loginpage = new LoginPage(driver);
		loginpage.userLoginProcedure(randomUser,
				InitializePropertyFile.property.getProperty("password"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAssetsAndServices();
		ReportDashboardPage dashboard = new ReportDashboardPage(driver);
		String widgetTitle = dashboard.selectRandomTicketWidget();
		dashboard.clickOnViewReport(widgetTitle);
		ViewReportPage report = new ViewReportPage(driver);
		report.blankRepairIDVerification(widgetTitle);
	}
}