package automated_tests.test;

import automated_tests.page_factory.Cart;
import automated_tests.page_factory.Home;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class TestCart {
    WebDriver driver = new ChromeDriver();

    Cart cart;
    Home home;




}
