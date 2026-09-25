package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest {
    protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private static final Properties properties = new Properties();

    static {

        try (InputStream input = BaseTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Не удалось загрузить config.properties, будут использованы дефолтные значения.");
        }
    }
    protected final String BASE_URL = getProperty("base.url", "http://youtrack-server:8080");
    protected final String GRID_URL = getProperty("grid.url", "http://localhost:4444/");
    protected final String ADMIN_USERNAME = getProperty("admin.username", "admin");
    protected final String ADMIN_PASSWORD = getProperty("admin.password", "123");

    private static String getProperty(String key, String defaultValue) {
        return System.getProperty(key, properties.getProperty(key, defaultValue));
    }
    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        URL gridUrl = new URL(GRID_URL);

        WebDriver driver = new RemoteWebDriver(gridUrl, options);
        driverThread.set(driver);
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {

        WebDriver driver = driverThread.get();
        if (driver != null) {
            driver.quit();
            driverThread.remove();
        }
    }

    protected WebDriver driver() {
        return driverThread.get();
    }
}