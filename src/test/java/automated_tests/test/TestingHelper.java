package automated_tests.test;

import org.openqa.selenium.WebDriver;

public class TestingHelper {
    private static final String rootPath = System.getProperty("user.dir") + "/";

    public static void setUp(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
        driver.get("http://localhost:8888/");
    }






}
