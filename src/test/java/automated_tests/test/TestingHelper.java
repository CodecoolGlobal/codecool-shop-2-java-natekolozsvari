package automated_tests.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class TestingHelper {

    public static void setUp(WebDriver driver, String url) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
        driver.get(url);
    }






}
