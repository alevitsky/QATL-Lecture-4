package myprojects.automation.assignment4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminProductsPage extends AdminPage {
    @CacheLookup
    @FindBy(id = "page-header-desc-configuration-add")
    private WebElement newProductLink;

    public AdminProductsPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AdminProductsPage waitLoadFinished() {
        waitPageReady();
        return this;
    }
    
    public NewProductPage openNewProductForm() {
        newProductLink.click();
        return PageFactory.initElements(getDriver(), NewProductPage.class);
    }

}