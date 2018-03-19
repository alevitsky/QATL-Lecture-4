package myprojects.automation.assignment4.pages;

import myprojects.automation.assignment4.model.ProductData;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class ProductDetailsPage extends Page {
    @CacheLookup
    @FindBy(xpath = "//h1[@itemprop = 'name']")
    private WebElement productTitle;

    @CacheLookup
    @FindBy(xpath = "//div[@class='product-quantities']//span")
    private WebElement productQty;

    @CacheLookup
    @FindBy(xpath = "//span[@itemprop = 'price']")
    private WebElement productPrice;

    public ProductDetailsPage(final WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ProductDetailsPage waitLoadFinished() {
        waitPageReady();
        return this;
    }

    public ProductData getProductData() {
        String name = productTitle.getText();
        String quantity = productQty.getText().replaceAll("\\D+","").trim();
        int qty     = Integer.parseInt(quantity);

        DecimalFormatSymbols separators = new DecimalFormatSymbols();
        separators.setDecimalSeparator(',');
        DecimalFormat format = new DecimalFormat("0.#", separators);
        String rawPrice = productPrice.getText().replaceAll("[^0-9,]+","").trim();
        float price = 0;
        try {
            price = format.parse(rawPrice).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ProductData(name, qty, price);
    }

}