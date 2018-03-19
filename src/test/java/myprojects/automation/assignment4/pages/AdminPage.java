package myprojects.automation.assignment4.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Optional;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

/**
 * The base class for all admin pages 
 */
public abstract class AdminPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//*[@class = 'icon-refresh icon-spin icon-fw']")
    private WebElement loader;

    @CacheLookup
    @FindBy(xpath = "//*/div[@class = 'shop-list']/a[@class = 'link']")
    private WebElement mainPageLink;

    public AdminPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitPageLoader() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 3);
        wait.until(visibilityOf(loader));
        if (loader.isDisplayed()) {
            wait.until(not(visibilityOf(loader)));
        }
    }

    @Override
    public MainPage openMainPage() {
        getWait().until(visibilityOf(mainPageLink));
        String mainHandle = getDriver().getWindowHandle();
        clickWithJS(mainPageLink);
        Optional<String> newTab = getDriver().getWindowHandles()
                .stream()
                .filter(handle -> !handle.equals(mainHandle))
                .findFirst();
        getDriver().switchTo().window(newTab.orElse(mainHandle));
        return PageFactory.initElements(getDriver(), MainPage.class);
    }

    protected WebElement getLoader() {
        return loader;
    }

}