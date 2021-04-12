package demo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.ClientReviewPage;
import Pages.Xls_Reader;
import commons.TestBase;

public class ReviewTask extends TestBase {

	Xls_Reader reader = new Xls_Reader(
			"C:\\Users\\Admin\\Desktop\\AppsIerra\\Project_Barcodes\\src\\test\\resources\\New Microsoft Excel Worksheet.xlsx");
	String sheetName = "Sheet1";

	@BeforeMethod
	public void openPage() throws IOException {

		driver.get("https://clutch.co/web-developers");
	}

	@Test
	public void getReviewData() throws IOException {

		ClientReviewPage clientReview = new ClientReviewPage(driver);
		clientReview.selectCompany(reader, sheetName);
	}
}