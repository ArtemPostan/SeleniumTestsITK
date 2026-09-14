package based;

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

public class GridBaseTest {
    protected static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private static final Properties properties = new Properties();

    static {

        try (InputStream input = GridBaseTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Не удалось загрузить config.properties, будут использованы дефолтные значения.");
        }
    }

    protected final String BASE_URL = System.getProperty("base.url", properties.getProperty("base.url", "http://youtrack-server:8080"));

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        String gridUrlStr = System.getProperty("grid.url", properties.getProperty("grid.url", "http://localhost:4444/"));
        URL gridUrl = new URL(gridUrlStr);

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