package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HeaderPage extends BasePage {

    private final By userAvatar = By.xpath("//img[@data-test='avatar']");
    private final By appearanceMenuLink = By.xpath("//span[text()='Оформление']");

    private By getThemeRadioLocator(String themeValue) {
        return By.cssSelector(String.format("input[value='%s']", themeValue));
    }

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public void setTheme(String themeValue) {
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(userAvatar));
        avatar.click();

        WebElement appearanceLink = wait.until(ExpectedConditions.elementToBeClickable(appearanceMenuLink));
        appearanceLink.click();

        WebElement themeInput = wait.until(ExpectedConditions.presenceOfElementLocated(getThemeRadioLocator(themeValue)));

        clickElementViaJs(themeInput);
    }
}