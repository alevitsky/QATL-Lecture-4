package myprojects.automation.assignment4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductsPage extends Page {
    @CacheLookup
    @FindBy(className = "ui-autocomplete-input")
    private WebElement searchField;

    public AllProductsPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AllProductsPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public SearchResultsPage findProduct(final String productName) {
        searchField.sendKeys(productName);
        searchField.submit();
        return PageFactory.initElements(getDriver(), SearchResultsPage.class);
    }

}