package myprojects.automation.assignment4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//*[@class='all-product-link pull-xs-left pull-md-right h4']")
    private WebElement allProductsLink;

    public MainPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public MainPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public AllProductsPage viewAllProducts() {
        scrollToElement(allProductsLink);
        clickWithJS(allProductsLink);
        return PageFactory.initElements(getDriver(), AllProductsPage.class);
    }

}