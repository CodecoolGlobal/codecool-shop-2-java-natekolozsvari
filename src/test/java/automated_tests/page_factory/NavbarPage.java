package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavbarPage {
    WebDriver driver;
    WebDriverWait wait;

    public NavbarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    WebElement homeBtn;

    @FindBy(id = "filter-btn")
    WebElement filterBtn;

    @FindBy(css = "body > div.topnav > a.shopcart")
    WebElement cartBtn;

    @FindBy(id = "login-btn")
    WebElement loginBtn;

    @FindBy(id = "signup-btn")
    WebElement signUpBtn;

    @FindBy(xpath = "//span[contains(text(),'Empty Cart')]")
    WebElement emptyCart;


    public void clickOnCartButton() {
        cartBtn.click();
    }

    public void clickOnEmptyCartButton(){
        emptyCart.click();
    }

    public void waitUntilCartButtonIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
    }

}
