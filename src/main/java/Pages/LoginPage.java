package Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//a[@class='logo']")
	WebElement barcodesLogo;

	@FindBy(xpath = "//a[@href='/store/customer/account/']")
	WebElement myAccount;

	@FindBy(xpath = "//span[contains(text(),'my account')]")
	WebElement myAccountButton;

	@FindBy(xpath = "//input[@name='login[username]']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@name='login[password]']")
	WebElement passwordField;

	@FindBy(xpath = "//button[@title='Login']")
	WebElement loginButton;

	@FindBy(xpath = "//a[@class='paginate_button next']//preceding-sibling::span//a")
	List<WebElement> pages;

	private static final Logger lOGGER = LogManager.getLogger(LoginPage.class.getName());

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void userLoginProcedure(String username, String password) {

		wait.forElementToBeVisible(myAccount);
		js.clickElement(myAccount);
		lOGGER.info("clicked on My Account");

		String pageTitle = driver.getTitle();

		if (pageTitle.contains("Customer Login")) {

			wait.forElementToBeVisible(myAccountButton);
			js.clickElement(myAccountButton);
			lOGGER.info("clicked on My Account");
		}

		wait.forElementToBeVisible(usernameField);
		sendKeys(usernameField, username);
		lOGGER.info("Entering the username/ email");

		wait.forElementToBeVisible(passwordField);
		sendKeys(passwordField, password);
		lOGGER.info("entering the password");

		wait.forElementToBeVisible(loginButton);
		js.clickElement(loginButton);
		lOGGER.info("clicked on Login Button");
	}

	public void selectRandomPage() {

		List<Integer> assets = new ArrayList<Integer>();

		String last = driver
				.findElement(By
						.xpath("//a[@class='paginate_button next']//preceding-sibling::span//a[" + pages.size() + "]"))
				.getText();

		int lastPage = Integer.parseInt(last);

		for (int i = 0; i < lastPage; i++) {

			assets.add(i + 1);
		}

		Random r = new Random();
		int nextRandomNumberIndex = r.nextInt(assets.size());
		System.out.println(assets.get(nextRandomNumberIndex));

		for (int i = 0; i <= nextRandomNumberIndex; i++) {

			pause(1000);
			driver.findElement(By.xpath("//a[contains(text(),'>')]")).click();
		}
	}
}
