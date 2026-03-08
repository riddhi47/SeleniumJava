package ats.TestCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import ats.Base.BaseTest;
import ats.pages.CartPage;
import ats.pages.CheckoutPage;
import ats.pages.HomePage;
import ats.pages.LoginPage;
import ats.utilities.ConfigReader;
import ats.utilities.CsvReaderUtil;


public class ATS_EndToEndTest extends BaseTest {
	
	// Main test case for complete order flow
	@Test
	public void verifyEndToEndOrderFlow()
	{
		//Create page object instances. Passing driver from BaseTest so all pages use same browser session
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        
        //Login to application. Username & password fetched from config.properties
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        
        //Navigate to home page to explore products
        homePage.goToHome();
        
        //Fetch product names from CSV file. This supports data-driven testing.
        List<String> products = CsvReaderUtil.getProductNames();
        
        //Add each product from CSV into cart one by one.
        for(String product:products)
        {
        	homePage.addProduct(product);
        }
        
        //Verify all expected products are present in cart. If any product missing → test will fail here.
        Assert.assertTrue(cartPage.areProductsPresent(products),"Product missing in cart");
        
        //Perform checkout and place order
        checkoutPage.placeOrder();
        
        //Verify order success message displayed
        Assert.assertTrue(checkoutPage.isOrderSuccess(),"Order Success message not displayed");
	}
}
