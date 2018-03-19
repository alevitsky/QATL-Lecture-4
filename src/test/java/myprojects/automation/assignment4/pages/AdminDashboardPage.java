package myprojects.automation.assignment4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AdminDashboardPage extends AdminPage {
    @CacheLookup
    @FindBy(id = "subtab-AdminCatalog")
    private WebElement catalogMenuItem;
    
    @CacheLookup
    @FindBy(id = "subtab-AdminProducts")
    private WebElement productsMenuItem;

    public AdminDashboardPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AdminDashboardPage waitLoadFinished() {
        waitPageLoader();
        return this;
    }

    public AdminProductsPage openProductsPage() {
        getWait().until(elementToBeClickable(catalogMenuItem));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(catalogMenuItem).click(productsMenuItem).build().perform();
        return PageFactory.initElements(getDriver(), AdminProductsPage.class);
    }

}