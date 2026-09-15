package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommentPage extends BasePage {

    public CommentPage(WebDriver driver) {
        super(driver);
    }

    private final By issuesButton = By.cssSelector("[data-test~='issues-button']");
    private final By issue = By.cssSelector("[data-test~='ticket-id']");
    private final By issueCommentField = By.cssSelector("[data-test~='wysiwyg-editor-content']");
    private final By submitButton = By.cssSelector("[data-test~='post-comment']");
    private final By confirmButton = By.cssSelector("[data-test~='confirm-ok-button']");

    public void createComment(String message) {

        WebElement issues = wait.until(
                ExpectedConditions.elementToBeClickable(issuesButton)
        );
        issues.click();

        WebElement ticket = wait.until(
                ExpectedConditions.elementToBeClickable(this.issue)
        );
        ticket.click();

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
