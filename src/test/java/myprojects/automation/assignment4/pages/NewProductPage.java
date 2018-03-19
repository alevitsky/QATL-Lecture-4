package myprojects.automation.assignment4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class NewProductPage extends AdminPage {
    @CacheLookup
    @FindBy(id = "form_step1_name_1")
    private WebElement newProductName;

    @CacheLookup
    @FindBy(id = "form_step1_qty_0_shortcut")
    private WebElement newProductQuantity;

    @CacheLookup
    @FindBy(id = "form_step1_price_ttc_shortcut")
    private WebElement newProductPrice;
    
    @CacheLookup
    @FindBy(className = "switch-input")
    private WebElement activationSwitch;

    @CacheLookup
    @FindBy(id = "growls")
    private WebElement flashMessage;

    @CacheLookup
    @FindBy(className = "growl-close")
    private WebElement close;

    @FindBy(xpath = "//*[@class = 'btn btn-primary js-btn-save']")
    private WebElement saveButton;

    public NewProductPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public NewProductPage waitLoadFinished() {
        waitPageReady();
        return this;
    }
    
    public NewProductPage fillProductName(final String productName) {
        newProductName.sendKeys(productName);
        return this;
    }

    public NewProductPage fillProductQuantity(final Integer productQuantity) {
        newProductQuantity.sendKeys(productQuantity.toString());
        return this;
    }

    public NewProductPage fillProductPrice(final String productPrice) {
        newProductPrice.sendKeys(productPrice);
        newProductPrice.submit();
        return this;
    }

    public NewProductPage activateProduct() {
        activationSwitch.click();
        return this;
    }

    public NewProductPage closeStatusMessage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 3);
        wait.until(visibilityOf(close));
        close.click();
        return this;
    }

    public NewProductPage saveProduct() {
        clickWithJS(saveButton);
        return this;
    }

}