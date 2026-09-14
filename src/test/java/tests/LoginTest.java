package tests;

import based.GridBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

public class LoginTest extends GridBaseTest {
    @Test
    public void testSuccessfulLogin() {

        driver().get(BASE_URL);

        LoginPage loginPage = new LoginPage(driver());
        loginPage.login("admin", "123");

        String currentUrl = driver().getCurrentUrl();
        Assertions.assertFalse(currentUrl.contains("login"), "Login failed, still on the login page!");
    }
}
