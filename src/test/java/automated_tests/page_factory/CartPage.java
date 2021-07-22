package automated_tests.page_factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"itemPrice\"]")
    WebElement cartElmoPrice;


    @FindBy(xpath = "//body/div[@id='myModal']/div[1]/div[2]/div[1]/p[1]")
    WebElement cartAlabamaPrice;

    @FindBy(css = ".empty_cart")
    WebElement emptyCartButton;

    @FindBy(css = ".close:nth-child(2)")
    WebElement closeModalButton;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public String getElmoPriceFromCart() {
        System.out.println(cartElmoPrice.getText());
        return cartElmoPrice.getText();
    }

    public void waitUntilCartElmoPriceIsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(cartElmoPrice, "PRICE: 29.97 USD"));
    }

    public void clickOnEmptyCartButton() {
        emptyCartButton.click();
    }


}
