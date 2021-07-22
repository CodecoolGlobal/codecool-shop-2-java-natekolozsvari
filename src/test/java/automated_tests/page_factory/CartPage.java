package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CartPage {
    WebDriver driver;

    @FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/p[1]/span[1]")
    WebElement cartElmoPrice;


    @FindBy(xpath = "//body/div[@id='myModal']/div[1]/div[2]/div[1]/p[1]")
    WebElement cartAlabamaPrice;



    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }
}
