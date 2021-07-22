package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CheckoutPage {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    @FindBy(id = "name")
    WebElement name;

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "phoneNumber")
    WebElement phoneNumber;

    @FindBy(id = "country")
    WebElement country;

    @FindBy(id = "address")
    WebElement address;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "zip")
    WebElement zip;

    @FindBy(xpath = "//button[contains(text(),'Continue to pay with credit card')]")
    WebElement payWithCreditCard;

    @FindBy(xpath = "//button[contains(text(),'Or Pay with PayPal')]")
    WebElement payWithPaypal;

    private void fillNameField(String nameInput) {
        name.sendKeys(nameInput);
    }

    private void fillEmailField(String emailInput) {
        email.sendKeys(emailInput);
    }

    private void fillPhoneNumberField(String phoneNumberInput) {
        phoneNumber.sendKeys(phoneNumberInput);
    }

    private void fillCountryField(String countryInput) {
        country.sendKeys(countryInput);
    }

    private void fillAddressField(String addressInput) {
        address.sendKeys(addressInput);
    }

    private void fillCityField(String cityInput) {
        city.sendKeys(cityInput);
    }

    private void fillZipField(String zipInput) {
        zip.sendKeys(zipInput);
    }

    public void clickPayWithCreditCard() {
        payWithCreditCard.click();
    }

    public void clickPayWithPaypal() {
        payWithPaypal.click();
    }

    public void fillBillingAddressForm(String nameInput, String emailInput, String phoneNumberInput,
                                       String countryInput, String addressInput, String cityInput, String zipInput) {
        fillNameField(nameInput);
        fillEmailField(emailInput);
        fillPhoneNumberField(phoneNumberInput);
        fillCountryField(countryInput);
        fillAddressField(addressInput);
        fillCityField(cityInput);
        fillZipField(zipInput);
    }
}
