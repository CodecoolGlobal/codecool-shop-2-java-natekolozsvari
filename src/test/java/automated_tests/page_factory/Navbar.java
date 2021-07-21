package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navbar {
    WebDriver driver;

    public Navbar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

}
