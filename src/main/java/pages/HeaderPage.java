package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeaderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By userAvatar = By.cssSelector("[data-test='avatar']");
    private final By appearanceMenuLink = By.xpath("//span[contains(@class, 'header__profile-link-like-item') and text()='Оформление']");
    private final By issuesButton = By.cssSelector("[data-test~='issues-button']");
    private final By issue = By.cssSelector("[data-test~='ticket-id']");
    private final By issueCommentField = By.cssSelector("[data-test~='wysiwyg-editor-content']");
    private final By submitButton = By.cssSelector("[data-test~='post-comment']");
    private final By confirmButton = By.cssSelector("[data-test~='confirm-ok-button']");

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void clickElementViaJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void setTheme(String themeValue) {
        WebElement avatar = wait.until(ExpectedConditions.elementToBeClickable(userAvatar));
        avatar.click();

        WebElement appearanceLink = wait.until(ExpectedConditions.elementToBeClickable(appearanceMenuLink));
        appearanceLink.click();

        By themeRadio = By.cssSelector("input[value='" + themeValue + "']");
        WebElement themeInput = wait.until(ExpectedConditions.presenceOfElementLocated(themeRadio));

        clickElementViaJs(themeInput);

        try { Thread.sleep(300); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public void createComment(String message) {

        WebElement issues = wait.until(
                ExpectedConditions.elementToBeClickable(issuesButton)
        );
        issues.click();

        WebElement ticket = wait.until(
                ExpectedConditions.visibilityOfElementLocated(this.issue)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(ticket)
        ).click();

        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(issueCommentField)
        );

        input.click();
        input.sendKeys(message);

        WebElement submit = wait.until(
                ExpectedConditions.elementToBeClickable(this.submitButton)
        );

        submit.click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("[data-test='comment-content']")
                )
        );
    }

    public String getCommentText(String text) {

        By commentLocator = By.xpath(
                "//*[@data-test='comment-content' and normalize-space()='" + text + "']"
        );

        WebElement comment = wait.until(
                ExpectedConditions.visibilityOfElementLocated(commentLocator)
        );

        return comment.getText();
    }

    public void deleteComment(String text) {

        By commentLocator = By.xpath(
                "//*[@data-test='change-item']" +
                        "//*[@data-test='comment-content' and normalize-space()='" + text + "']"
        );

        WebElement comment = wait.until(
                ExpectedConditions.visibilityOfElementLocated(commentLocator)
        );

        WebElement changeItem = comment.findElement(
                By.xpath("./ancestor::*[@data-test='change-item']")
        );

        Actions actions = new Actions(driver);
        actions.moveToElement(changeItem).perform();

        WebElement commentMenu = changeItem.findElement(
                By.cssSelector("[data-test='comment-menu']")
        );

        wait.until(ExpectedConditions.elementToBeClickable(commentMenu))
                .click();

        WebElement deleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("span[title='Удалить']")
                )
        );

        deleteButton.click();

        WebElement confirmButtonElement = wait.until(
                ExpectedConditions.elementToBeClickable(confirmButton)
        );

        confirmButtonElement.click();
    }
}