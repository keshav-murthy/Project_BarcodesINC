package Pages;

import java.util.List;

import javax.tools.DocumentationTool.Location;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class AssetDetailsPage extends BasePage {

	protected static String firstName;

	@FindBy(xpath = "//div[@class='item-details-left-holder']//img")
	WebElement realImage;

	@FindBy(xpath = "//div//label[contains(text(),'Location : ')]//following-sibling::span")
	WebElement locationDetails;

	@FindBy(xpath = "//input[@value='Add Contract']")
	WebElement addContract;

	@FindBy(xpath = "//input[@value='Create Ticket']")
	WebElement createTicket;

	@FindBy(xpath = "//div[@class='item-details-right-holder']")
	WebElement assetDetails;

	@FindBy(xpath = "//div//label[contains(text(),'Serial Number : ')]//following-sibling::span")
	WebElement serialNumber;

	@FindBy(xpath = "(//table[@id='ticket-table']//td[1])[1]//a")
	WebElement firstTicket;

	@FindBy(xpath = "//table[@id='ticket-table']//td[1]")
	List<WebElement> ticketID;

	@FindBy(xpath = "//table[@id='ticket-table']//td[3]")
	List<WebElement> ticketTitle;

	@FindBy(xpath = "(//table[@id='contarcts-table']//td[1])[1]")
	WebElement firstContractName;

	@FindBy(xpath = "(//table[@id='contarcts-table']//td[1])[2]")
	WebElement secondContractName;

	@FindBy(xpath = "//p[@class='no-contract']")
	WebElement noContract;

	private static final Logger lOGGER = LogManager.getLogger(AssetDetailsPage.class.getName());

	public AssetDetailsPage(WebDriver driver) {
		super(driver);
	}

	public void clickOnCreateTicket() {

		wait.forElementToBeVisible(createTicket);
		click(createTicket);
		lOGGER.info("click on Create Ticket button");
	}

	public void fetchAssetDetails(String random) {
		wait.forElementToBeVisible(assetDetails);
		System.out.println(assetDetails.getText());
		String actual = serialNumber.getText();
		String expected = random;
		Assert.assertEquals(actual, expected);
		lOGGER.info("Fetching details of asset added and also verified the serial number");
	}

	public void clickOnAddContract() {

		wait.forElementToBeVisible(addContract);
		click(addContract);
		lOGGER.info("click on Add Contract button");
	}

	public void ticketDetailsOfAsset() {

		String noData = "No data available in table";
		wait.forElementToBeVisible(serialNumber);

		for (int i = 0; i < ticketID.size(); i++) {
			if (!((ticketID.get(i).getText()).equals(noData))) {
				System.out.println("Ticket ID of this " + serialNumber.getText() + " Asset serial number is :- "
						+ ticketID.get(i).getText());
				System.out.println("Ticket title of this " + serialNumber.getText() + " Asset serial number is :- "
						+ ticketTitle.get(i).getText());
			} else {
				System.out.println(noData);
			}
		}
	}

	public void clickOnFirstTicket() {

		wait.forPage(2000);
		try {
			click(firstTicket);
			lOGGER.info("Clicking on first Ticket from the list");
		} catch (Exception e) {
			System.out.println("No tickets created yet for this asset");
		}
	}

	public String getFirstContractName() {

		wait.forPage(2000);
		try {
			lOGGER.info("Clicking on name of first contract from the list");
			firstName = firstContractName.getText();
			System.out.println(firstName);

		} catch (NoSuchElementException e) {

			System.out.println(serialNumber.getText());
			System.out.println(noContract.getText());
			firstName = noContract.getText();
		}
		return firstName;
	}

	public String getSecondContractName() {

		wait.forElementToBeVisible(secondContractName);
		lOGGER.info("Clicking on name of second contract from the list");
		return secondContractName.getText();
	}

	public void imageVerfication() {

		wait.forElementToBeVisible(realImage);
		Assert.assertEquals(realImage.isDisplayed(), true,
				"The Real Image for this Asset is not displayed on the screen");
	}

	public void locationValidation(String location) {

		try {
			Assert.assertEquals(((locationDetails.getText()).contains(location)), true);
		} catch (NoSuchElementException e) {
			System.out.println(serialNumber.getText());
			System.out.println("The location for the respective Asset is not displayed");
		}
	}
}