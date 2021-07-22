package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NavbarPage {
    WebDriver driver;

    public NavbarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    @FindBy(xpath = "//a[contains(text(),'Home')]")
    WebElement homeBtn;

    @FindBy(id = "filter-btn")
    WebElement filterBtn;

    @FindBy(css = ".shopcart")
    WebElement cartBtn;

    @FindBy(id = "login-btn")
    WebElement loginBtn;

    @FindBy(id = "signup-btn")
    WebElement signUpBtn;


    public void clickOnCartButton() {
        cartBtn.click();
    }
}
