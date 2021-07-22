package automated_tests.test;

import automated_tests.page_factory.CheckoutPage;
import automated_tests.page_factory.PaymentPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class TestCheckout {

    WebDriver driver = new ChromeDriver();
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;

    @BeforeEach
    void setUp() {
        TestingHelper.setUp(driver, "http://localhost:8888/checkout");
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/successfulCheckoutData.csv", numLinesToSkip = 1)
    void testSuccessfulCheckoutRedirectsToPaymentPage(String name, String email, String phoneNumber,
                                                      String country, String address, String city, String zip) {
        checkoutPage.fillBillingAddressForm(name, email, phoneNumber, country, address, city, zip);
        checkoutPage.clickPayWithCreditCard();
        assertTrue(paymentPage.paymentHeaderIsDisplayed());
    }
}

