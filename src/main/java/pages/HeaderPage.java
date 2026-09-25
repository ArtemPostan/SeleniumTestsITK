package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HeaderPage extends BasePage {

    private final By userAvatar = By.xpath("//img[@data-test='avatar']");
    private final By appearanceMenuLink = By.xpath("//span[text()='Оформление']");

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