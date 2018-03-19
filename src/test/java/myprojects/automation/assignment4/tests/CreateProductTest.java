package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class CreateProductTest extends BaseTest {

    @Test(dataProvider = "credentials")
    public void createNewProduct(String login, String password) {        
        actions.login(login, password);
        actions.createProduct(testProduct);
    }

    @Test(dependsOnMethods = "createNewProduct")
    public void checkProductVisibility() {
        actions.openMainPage();
        actions.waitForContentLoad();
        actions.viewAllProducts();
        actions.findProduct(testProduct.getName());
        assertTrue(actions.hasProduct(testProduct.getName()), "The given product is not found.");
        ProductData resultProduct = actions.getProductDetails(testProduct.getName());
        assertEquals(testProduct.getName().toUpperCase(), resultProduct.getName());
        assertEquals(testProduct.getQty(), resultProduct.getQty());
        assertEquals(testProduct.getPrice(), resultProduct.getPrice());
    }
}
