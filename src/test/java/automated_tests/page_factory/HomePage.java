package automated_tests.page_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    @FindBy(xpath = "//div[@id='toys']/div/div/div[3]/div/p")
    WebElement elmoPrice;

    @FindBy(xpath = "//div[@id='toys']/div[2]/div/div[3]/div/p")
    WebElement elmoAddToCart;

    @FindBy(xpath = "//div[@id='toys']/div[2]/div/div[3]/div/p")
    WebElement alabamaPrice;

    @FindBy(xpath = "//div[@id='toys']/div/div/div[3]/div/p")
    WebElement alabamaAddToCart;

    @FindBy(xpath = "//div[@id='toys']/div[3]/div/div[3]/div/p")
    WebElement chinpokomonPrice;

    @FindBy(xpath = "//div[@id='toys']/div[3]/div/div[3]/div[2]/a")
    WebElement chinpokomonAddToCart;

    @FindBy(xpath = "//*[@id=\"vehicles\"]/div[1]/div/div[3]/div[1]/p")
    WebElement actionBikePrice;

    @FindBy(xpath = "//*[@id=\"vehicles\"]/div[1]/div/div[3]/div[2]/a")
    WebElement actionBikeAddToCart;

    @FindBy(xpath = "//*[@id=\"vehicles\"]/div[2]/div/div[3]/div[1]/p")
    WebElement futuristicBikePrice;

    @FindBy(xpath = "//*[@id=\"vehicles\"]/div[2]/div/div[3]/div[2]/a")
    WebElement futuristicBikeAddToCart;

    @FindBy(xpath = "//*[@id=\"musics\"]/div[1]/div/div[3]/div[1]/p")
    WebElement faithPrice;

    @FindBy(xpath = "//*[@id=\"musics\"]/div[1]/div/div[3]/div[2]/a")
    WebElement faithAddToCart;

    @FindBy(xpath = "//*[@id=\"musics\"]/div[2]/div/div[3]/div[1]/p")
    WebElement vientosPrice;

    @FindBy(xpath = "//*[@id=\"musics\"]/div[2]/div/div[3]/div[2]/a")
    WebElement vientosAddToCart;


    public void clickOnElmoAddToCart() {
        elmoAddToCart.click();
    }

    public String getElmoPriceFromHomePage() {
        return elmoPrice.getText();
    }

    public void waitUntilHomePageIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(elmoAddToCart));
    }
}
