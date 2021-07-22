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

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/p[1]/span[1]")
    WebElement cartElmoPrice;


    @FindBy(xpath = "//body/div[@id='myModal']/div[1]/div[2]/div[1]/p[1]")
    WebElement cartAlabamaPrice;



    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public String getElmoPriceFromCart() {
        return cartElmoPrice.getText();
    }

    public void waitUntilCartElmoPriceIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cartElmoPrice));
    }

}
