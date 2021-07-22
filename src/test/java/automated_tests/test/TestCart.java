package automated_tests.test;

import automated_tests.page_factory.CartPage;
import automated_tests.page_factory.HomePage;
import automated_tests.page_factory.NavbarPage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCart {
    WebDriver driver = new ChromeDriver();

    HomePage homePage;
    NavbarPage navbarPage;
    CartPage cartPage;

    @BeforeEach
    void setUp() throws IOException {
        TestingHelper.setUp(driver, "http://localhost:8888/");
        homePage = new HomePage(driver);
        navbarPage = new NavbarPage(driver);
        cartPage = new CartPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }


    @Test
    public void addProductToCart_priceInCartIsEqualToPriceInHomePage(){
//        homePage.waitUntilHomePageIsClickable();
        homePage.clickOnElmoAddToCart();
        navbarPage.clickOnCartButton();
        assertEquals(homePage.getElmoPriceFromHomePage(), cartPage.getElmoPriceFromCart());
    }




}
