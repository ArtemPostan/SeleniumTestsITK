package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HeaderPage extends BasePage {

    private final By userAvatar = By.cssSelector("[data-test='avatar']");
    private final By appearanceMenuLink = By.cssSelector(".header__profile-link-like-item");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void setTheme(String themeValue) {
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(userAvatar));
        avatar.click();

        WebElement appearanceLink = wait.until(ExpectedConditions.elementToBeClickable(appearanceMenuLink));
        appearanceLink.click();

        By themeRadio = By.cssSelector("input[value='" + themeValue + "']");
        WebElement themeInput = wait.until(ExpectedConditions.presenceOfElementLocated(themeRadio));

        clickElementViaJs(themeInput);
    }
}