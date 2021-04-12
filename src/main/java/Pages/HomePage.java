package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class HomePage extends BasePage {

	String pageTitle = driver.getTitle();

	@FindBy(xpath = "//span[contains(text(),'My Assets')]")
	WebElement myAssets;

	@FindBy(xpath = "//button[@name='agree']")
	WebElement agreeButton;

	private static final Logger lOGGER = LogManager.getLogger(HomePage.class.getName());

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOnMyAssetsAndServices() {

		wait.forElementToBeVisible(myAssets);
		click(myAssets);
		lOGGER.info("clicked on My Assets & Services Button");
		if (pageTitle.contains("To access the Assets & Services portal")) {

			wait.forElementToBeVisible(agreeButton);
			js.clickElement(agreeButton);
			lOGGER.info("clicked on terms & conditions Agree button");
		}
	}

	public void verifyServicePortal() {

		wait.forElementToBeVisible(myAssets);
		boolean actual = myAssets.isDisplayed();
		Assert.assertTrue(actual);
		lOGGER.info("Verifying the presence of My Assets & Services Button");
	}
}
