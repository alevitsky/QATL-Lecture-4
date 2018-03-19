package myprojects.automation.assignment4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends Page {
    @CacheLookup
    private WebElement productMiniature;

    public SearchResultsPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public SearchResultsPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public boolean containsProduct(final String productName) {
        findProductMiniature(productName);
        return productMiniature.isDisplayed();
    }

    public ProductDetailsPage viewProductDetails(final String productName) {
        findProductMiniature(productName);
        if (containsProduct(productName)) productMiniature.click();
        return PageFactory.initElements(getDriver(), ProductDetailsPage.class);
    }

    private void findProductMiniature(final String productName) {
        String expr = String.format("//*[@class = 'h3 product-title']//a[contains(text(),'%s')]", productName);
        productMiniature = getDriver().findElement(By.xpath(expr));
    }

}