package based;

import dto.UserLoginData;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;

import java.net.MalformedURLException;

public class AuthorizedGridBaseTest extends GridBaseTest {
    @Override
    @BeforeEach
    public void setUp() throws MalformedURLException {
        super.setUp();
        LoginPage loginPage = new LoginPage(driver());
        UserLoginData admin = new UserLoginData("admin", "123");
        loginPage.login(admin);
    }
}
