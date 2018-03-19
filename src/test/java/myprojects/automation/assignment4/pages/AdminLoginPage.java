package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.utils.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage extends AdminPage {
    @CacheLookup
    @FindBy(id = "email")    
    private WebElement emailInput;
    
    @CacheLookup
    @FindBy(id = "passwd")
    private WebElement passwordInput;

    @CacheLookup    
    @FindBy(name = "submitLogin")
    private WebElement loginButton;

    public AdminLoginPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public AdminLoginPage waitLoadFinished() {
        waitPageLoader();
        return this;
    }

    public AdminLoginPage open() {
        getDriver().get(Properties.getBaseAdminUrl());
        return this;
    }

    public AdminLoginPage fillEmail(final String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public AdminLoginPage fillPassword(final String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public AdminDashboardPage submitLogin() {
        loginButton.click();
        return PageFactory.initElements(getDriver(), AdminDashboardPage.class);
    }

}