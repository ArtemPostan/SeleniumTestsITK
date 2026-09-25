package tests;

import dto.UserLoginData;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.time.Duration;

public class AuthorizedGridBaseTest extends BaseTest {

    @Override
    @BeforeEach
    public void setUp() throws MalformedURLException {
        super.setUp();
        LoginPage loginPage = new LoginPage(driver());
        UserLoginData admin = new UserLoginData(ADMIN_USERNAME, ADMIN_PASSWORD);

        loginPage.login(admin);
        new WebDriverWait(driver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='avatar']")));

    }
}
