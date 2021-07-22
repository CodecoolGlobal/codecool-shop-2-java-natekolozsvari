package automated_tests.test;

import automated_tests.page_factory.CartPage;
import automated_tests.page_factory.HomePage;
import automated_tests.page_factory.NavbarPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCart {
    WebDriver driver = new ChromeDriver();

    HomePage homePage;
    NavbarPage navbarPage;
    CartPage cartPage;

    @BeforeEach
    void setUp() {
        TestingHelper.setUp(driver, "http://localhost:8888/");
        homePage = new HomePage(driver);
        navbarPage = new NavbarPage(driver);
        cartPage = new CartPage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='67%'");
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void addProductToCart_priceInCartIsEqualToPriceInHomePage(){
        homePage.waitUntilHomePageIsClickable();
        homePage.clickOnElmoAddToCart();
        String homePagePrice = homePage.getElmoPriceFromHomePage();
        navbarPage.waitUntilCartButtonIsClickable();
        navbarPage.clickOnCartButton();
//        cartPage.waitUntilCartElmoPriceIsVisible();
        assertEquals(homePagePrice, cartPage.getElmoPriceFromCart() + " USD");
        cartPage.clickOnEmptyCartButton();
    }




}
