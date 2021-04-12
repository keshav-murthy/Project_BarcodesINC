package Pages;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class ViewReportPage extends BasePage {

	@FindBy(xpath = "//h2")
	WebElement reportHeader;

	@FindBy(xpath = "//input[@id='from_date']")
	WebElement fromDate;

	@FindBy(xpath = "//input[@id='to_date']")
	WebElement toDate;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Export to CSV')]")
	WebElement export;

	@FindBy(xpath = "//td[@class='dataTables_empty']")
	WebElement emptyTable;

	@FindBy(xpath = "//tbody//tr//td[@class='reorder sorting_1']")
	List<WebElement> table;

	@FindBy(xpath = "//tbody//tr//td[@class=' reorder'][6]")
	List<WebElement> dateTable;

	@FindBy(xpath = "//tbody//tr//td[1]")
	List<WebElement> repairIDList;

	@FindBy(xpath = "//tbody//tr//td[2]")
	List<WebElement> serialNoList;

	@FindBy(xpath = "//th[text()='Serial No']")
	WebElement serialNo;

	@FindBy(xpath = "//th[text()='Subject']")
	WebElement subject;

	@FindBy(xpath = "//th[text()='Manufacturer']")
	WebElement manufacturer;

	@FindBy(xpath = "//th[text()='Model']")
	WebElement model;

	@FindBy(xpath = "//th[text()='Type']")
	WebElement type;

	@FindBy(xpath = "//th[text()='Status']")
	WebElement status;

	@FindBy(xpath = "//th[text()='Last Updated']")
	WebElement lastUpdated;

	@FindBy(xpath = "//div[@class='dataTables_info']")
	WebElement dataTableInfo;

	@FindBy(xpath = "//a[@class='paginate_button current']")
	WebElement currentPage;

	@FindBy(xpath = "//a[@class='paginate_button next']//preceding-sibling::span//a")
	List<WebElement> pages;

	@FindBy(xpath = "//a[contains(text(),'>')]")
	WebElement nextArrow;

	@FindBy(xpath = "//table//th[3]")
	WebElement firstColumn;

	@FindBy(xpath = "//table//th[4]")
	WebElement secondColumn;

	@FindBy(xpath = "//table//th[5]")
	WebElement thirdColumn;

	@FindBy(xpath = "//table//th[6]")
	WebElement fourthColumn;

	@FindBy(xpath = "//select[@name='ticket-table_length']")
	WebElement tableLengthDropDown;

	private static final Logger lOGGER = LogManager.getLogger(ViewReportPage.class.getName());

	public ViewReportPage(WebDriver driver) {
		super(driver);
	}

	public void verifyReportDetails(String expected) {

		wait.forPage(2000);
		wait.forElementToBeVisible(reportHeader);
		String actual = reportHeader.getText();
		Assert.assertEquals(expected, actual);

		lOGGER.info("Verifying the report details page Heading");
	}

	public void clickOnViewReport(String widgetTitle) {

		WebElement viewReport = driver.findElement(By.xpath(
				"//h3[contains(text()," + "'" + widgetTitle + "'" + ")]//ancestor::div[@class='inner']//div//a"));
		scrollToElementView(viewReport);
		viewReport.click();
	}

	public void validSearchVerification(String searchElement) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchElement);
		lOGGER.info("Entering the required data in search field");
		wait.forPage();
		String actualResult = driver.findElement(By.xpath("//td[contains(text()," + "'" + searchElement + "'" + ")]"))
				.getText();
		String expectedResult = searchElement;
		Assert.assertEquals(actualResult, expectedResult);
		lOGGER.info("Verifying search field with valid Serial Number");
	}

	public void invalidSearchVerification(String searchElement) {

		wait.forElementToBeVisible(search);
		sendKeys(search, searchElement);
		lOGGER.info("Entering the required data in search field");
		wait.forElementToBeVisible(emptyTable);
		System.out.println(emptyTable.getText());
		lOGGER.info("Verifying search field with Invalid Serial Number");
	}

	public void paginationVerification() {

		int lastPage = 0;
		System.out.println(pages.size());
		if ((pages.size()) == 0) {

			wait.forPage(1500);
			wait.forElementToBeVisible(dataTableInfo);
			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		} else {
			String last = driver
					.findElement(By.xpath(
							"//a[@class='paginate_button next']//preceding-sibling::span//a[" + pages.size() + "]"))
					.getText();
			try {
				lastPage = Integer.parseInt(last);
			} catch (Exception e) {
				lastPage = 1;
			}
			for (int i = 0; i < lastPage - 1; i++) {

				wait.forPage(1500);
				wait.forElementToBeVisible(currentPage);
				System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

				wait.forElementToBeVisible(dataTableInfo);
				System.out.println("Total contents in this page are :- " + dataTableInfo.getText());

				wait.forElementToBeVisible(nextArrow);
				click(nextArrow);
			}

			wait.forPage(1500);
			wait.forElementToBeVisible(currentPage);
			System.out.println("This is the Current page that is focused on :- " + currentPage.getText());

			wait.forElementToBeVisible(dataTableInfo);
			System.out.println("Total contents in this page are :- " + dataTableInfo.getText());
		}
	}

//	public void sortingVerification() {
//
//		wait.forElementToBeVisible(manufacturer);
//		click(manufacturer);
//		wait.forElementToBeVisible(manufacturer);
//		click(manufacturer);
//		lOGGER.info("Sorting the Manufacturer column in Descending order");
//		printTableContent();
//
//		wait.forElementToBeVisible(model);
//		click(model);
//		wait.forElementToBeVisible(model);
//		click(model);
//		lOGGER.info("Sorting the Model column in Descending order");
//		printTableContent();
//
//		wait.forElementToBeVisible(type);
//		click(type);
//		wait.forElementToBeVisible(type);
//		click(type);
//		lOGGER.info("Sorting the type column in Descending order");
//		printTableContent();
//
//		wait.forElementToBeVisible(lastUpdated);
//		click(lastUpdated);
//		lOGGER.info("Sorting the Last Updated column in Ascending order");
//		printTableContent();
//	}

	public void printTableContent() {

		try {
			while (emptyTable.isDisplayed() == true) {
				System.out.println(emptyTable.getText());
			}
		} catch (NoSuchElementException e) {
			List<WebElement> content = table;
			for (int i = 0; i < content.size(); i++) {
				pause(500);
				System.out.println(
						"displaying details of table after sorting of column :----" + content.get(i).getText());
			}
		}
	}

	public void dateRangeVerification(String startDate, String endDate) {

		wait.forElementToBeVisible(fromDate);
		javaScriptSendValue(fromDate, startDate);
		lOGGER.info("Entering the starting date in date field");

		wait.forElementToBeVisible(toDate);
		javaScriptSendValue(toDate, endDate);
		lOGGER.info("Entering the ending date in date field");

		pause(5000);
		List<WebElement> dateContent = dateTable;
		for (int i = 0; i < dateContent.size(); i++) {
			System.out.println(
					"displaying details of table after sorting of column :----" + dateContent.get(i).getText());
		}
	}

	public void settingDownloadPath() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePref = new HashMap<>();
		chromePref.put("download.default_directory", "/Project_Barcodes");
		options.setExperimentalOption("prefs", chromePref);
	}

	public void sortingVerification() {

		pause(1000);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		pause(1000);
		wait.forElementToBeVisible(firstColumn);
		click(firstColumn);
		lOGGER.info("Sorting the " + firstColumn.getText() + " column in Descending order");
		printTableContent();

		pause(1000);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		pause(1000);
		wait.forElementToBeVisible(secondColumn);
		click(secondColumn);
		lOGGER.info("Sorting the " + secondColumn.getText() + " column in Descending order");
		printTableContent();

		pause(1000);
		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		pause(1000);
		wait.forElementToBeVisible(thirdColumn);
		click(thirdColumn);
		lOGGER.info("Sorting the " + thirdColumn.getText() + " column in Descending order");
		printTableContent();

		pause(1000);
		wait.forElementToBeVisible(fourthColumn);
		click(fourthColumn);
		lOGGER.info("Sorting the " + fourthColumn.getText() + " column in Descending order");
		printTableContent();
	}

	public void blankRepairIDVerification(String widgetTitle) {

		int count = 0;
		wait.forPage();
		wait.forElementToBeVisible(tableLengthDropDown);
		dropDownMethod(tableLengthDropDown, "VisibleText", "All");

		wait.forPage();

		try {
			for (int i = 0; i < repairIDList.size(); i++) {

				String repairID = repairIDList.get(i).getText();
				String serialNo = serialNoList.get(i).getText();
				if (repairID.equals("")) {
					System.out.println("There is a blank Repair ID in " + widgetTitle
							+ " widget and Serial No associated with it is " + serialNo);
					count = count + 1;
				}
			}
			System.out.println("Total number of blank Repair ID's are " + count);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(emptyTable.getText());
		}
	}
}