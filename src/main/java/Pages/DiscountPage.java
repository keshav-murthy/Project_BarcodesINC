package Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.BasePage;

public class DiscountPage extends BasePage {

	@FindBy(xpath = "//input[@id='search']")
	public WebElement searchField;

	@FindBy(xpath = "//div[@class='form-search category-search ng-scope']//button[@title='Search']")
	public WebElement searchButton;

	@FindBy(xpath = "//div[@class='breadcrumbs']//strong")
	public WebElement breadcrumbTitle;

	@FindBy(xpath = "//p[@class='ss-ac-item-name ng-binding']")
	public List<WebElement> searchSuggestions;

	@FindBy(xpath = "//a//parent::li[@class='menu-static-width']")
	public List<WebElement> categories;

	@FindBy(xpath = "//h2[@class='product-name']")
	public List<WebElement> productsInCategories;

	@FindBy(xpath = "//button[@title='Add To Cart']")
	public List<WebElement> addToCart;
	
	@FindBy(xpath = "//span[@class='price']")
	public List<WebElement> productPrice;

	private static final Logger lOGGER = LogManager.getLogger(DiscountPage.class.getName());

	public DiscountPage(WebDriver driver) {
		super(driver);
	}

	public void enterProductToSearch(String product) {

		wait.forElementToBeVisible(searchField);
		sendKeys(searchField, product);
		wait.forElementToBeVisible(searchButton);
		click(searchButton);
		lOGGER.info("Entering the product to be searched on search field");
	}

	public void selectRandomSuggestionProduct() {

		int randomProduct = random.nextInt(searchSuggestions.size());
		wait.forElementToBeVisible(searchSuggestions.get(randomProduct / 2));
		click(searchSuggestions.get(randomProduct / 2));
		lOGGER.info("Searching for a random product among the list of suggestions");
	}

	public String selectRandomCategory() {

		action = new Actions(driver);
		int randomProduct = random.nextInt(categories.size());
		wait.forElementToBeVisible(categories.get(randomProduct));
		String category = categories.get(randomProduct).getText();
		action.moveToElement(categories.get(randomProduct)).perform();
		lOGGER.info("Mouse hover to random categories among the menu");
		return category;
	}

	public void selectRandomProductFromCatogoriesList(String category) {

		action = new Actions(driver);
		List<WebElement> list = driver
				.findElements(By.xpath("// a[text()=' " + category + "']//parent::li//li[@class='menu-item']"));
		System.out.println(list);
		int randomProduct = random.nextInt(list.size());
		wait.forElementToBeVisible(list.get(randomProduct));
		action.moveToElement(list.get(randomProduct)).doubleClick().perform();
//		action.doubleClick(list.get(randomProduct));
//		js.clickElement(list.get(randomProduct));
		lOGGER.info("clicking on random product from categories");
	}

	public void verifyProducts() {

		wait.forPage();
		for (int i = 0; i < productsInCategories.size(); i++) {
			wait.forPage();
			wait.forElementToBeVisible(productsInCategories.get(i));
			Assert.assertTrue(productsInCategories.get(i).isDisplayed());
			lOGGER.info("Verifying whether there are products in categories");
		}
	}

	public void verifyProductPrices() {
		
		for (int i = 0; i < productPrice.size(); i++) {
			wait.forElementToBeVisible(productPrice.get(i));
			Assert.assertTrue(productPrice.get(i).isDisplayed());
		}
		lOGGER.info("Verifying whether the Product's Price is visible or not");
	}
	
	public void verifyAddToCart() {

		for (int i = 0; i < addToCart.size(); i++) {
			wait.forElementToBeVisible(addToCart.get(i));
			Assert.assertTrue(addToCart.get(i).isDisplayed());
		}
		lOGGER.info("Verifying whether the Add To Cart button is visible or not");
	}

	public void verifyBreadcrumbs() {

		enterProductToSearch("Printer");
		wait.forElementToBeVisible(breadcrumbTitle);
		Assert.assertTrue(breadcrumbTitle.isDisplayed());
		lOGGER.info("Verifying the breadcrumbs in barcodes discount");
	}

	public void verifyPageRedirect() {

		enterProductToSearch("Scanner");
		String currentUrl = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("barcodegiant.com"));
		Assert.assertTrue(currentUrl.contains("https://www.barcodediscount.com/"));
		wait.forElementToBeVisible(breadcrumbTitle);
		Assert.assertTrue(breadcrumbTitle.isDisplayed());
		lOGGER.info("Verifying the page redirect upon searching");
	}

	public void verifySearchSuggestions() {

		wait.forElementToBeVisible(searchField);
		sendKeys(searchField, "Mobile Computing");
		selectRandomSuggestionProduct();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertFalse(currentUrl.contains("barcodegiant.com"));
		Assert.assertTrue(currentUrl.contains("https://www.barcodediscount.com/"));
		wait.forElementToBeVisible(breadcrumbTitle);
		Assert.assertTrue(breadcrumbTitle.isDisplayed());
		lOGGER.info("verifying the page redirect upn clicking random product from suggestion list");
	}

	public void verifyProductsInCategories() {

		String category = selectRandomCategory();
		System.out.println(category);
		selectRandomProductFromCatogoriesList(category);
		verifyProducts();
	}

	public void verifyPriceAndAddToCart() {

		verifyProductPrices();
		verifyAddToCart();
	}
}