package myprojects.automation.assignment4;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.pages.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final AtomicReference<Page> currentPage = new AtomicReference<>();
    private AdminDashboardPage dashboardPage;
    private MainPage mainPage;
    private AllProductsPage allProductsPage;
    private SearchResultsPage searchResultsPage;

    public GeneralActions(final WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
    }

    /**
     * Logs in to Admin Panel.
     * @param login
     * @param password
     */
    public void login(final String login, final String password) {
        AdminLoginPage loginPage = new AdminLoginPage(driver);
        this.dashboardPage = loginPage.open()
                                      .fillEmail(login)
                                      .fillPassword(password)
                                      .submitLogin();
        setCurrentPage(dashboardPage);
    }

    public void createProduct(final ProductData newProduct) {
        verifyPageIsOpened(dashboardPage, "The Dashboard page should be opened before creating the new product.");
        NewProductPage newProductPage = dashboardPage.openProductsPage()
                                                     .waitLoadFinished()
                                                     .openNewProductForm()
                                                     .waitLoadFinished();

        newProductPage.fillProductName(newProduct.getName())
                      .fillProductQuantity(newProduct.getQty())
                      .fillProductPrice(newProduct.getPrice())
                      .activateProduct()
                      .closeStatusMessage()
                      .saveProduct()
                      .closeStatusMessage();
        setCurrentPage(newProductPage);
    }

    public void openMainPage() {
        verifyPageIsAdmin(currentPage.get(), "Please open some page before following to the Main page.");
        this.mainPage = currentPage.get().openMainPage();
        setCurrentPage(mainPage);
    }

    public void viewAllProducts() {
        verifyPageIsOpened(mainPage, "The Main page should be opened before viewing products.");
        this.allProductsPage = mainPage.viewAllProducts();
        setCurrentPage(allProductsPage);
    }

    public void findProduct(final String productName) {
        verifyPageIsOpened(allProductsPage, "The All products page should be opened before searching for the product.");
        this.searchResultsPage = allProductsPage.findProduct(productName);
        setCurrentPage(searchResultsPage);
    }

    public boolean hasProduct(final String productName) {
        verifyPageIsOpened(searchResultsPage, "The check for the presence of the product possible only on the Search results page.");
        return searchResultsPage.containsProduct(productName);
    }

    public ProductData getProductDetails(final String productName) {
        verifyPageIsOpened(searchResultsPage, "You can view Product details only when one of the site pages is opened.");
        ProductDetailsPage productDetails = searchResultsPage.viewProductDetails(productName);
        setCurrentPage(productDetails);
        return productDetails.getProductData();
    }

    /**
     * Waits until page loader disappears from the page
     */
    public void waitForContentLoad() {
        verifyPageIsOpened(currentPage.get(), "Please open some page before the waiting for content to load.");
        currentPage.get().waitLoadFinished();
    }

    private void setCurrentPage(final Page page) {
        this.currentPage.set(page);
    }

    private void verifyPageIsOpened(final Page page, final String errorMessage) {
        if (page == null) {
            throw new IllegalStateException(errorMessage);
        }
    }

    private void verifyPageIsAdmin(final Page page, final String errorMessage) {
        if (page == null) {
            throw new IllegalStateException(errorMessage);
        }
    }

}