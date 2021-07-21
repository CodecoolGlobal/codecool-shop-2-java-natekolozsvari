package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Filter {
    WebDriver driver;

    public Filter(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
