package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssuesPage extends BasePage {
    public IssuesPage(WebDriver driver) {
        super(driver);
    }

    private final By createButtonInHeader = By.cssSelector("button[data-test='ring-link'] span[data-test='undefined-title']");
    private final By newIssueMenuItem = By.cssSelector("[href*='newIssue']");
    private final By summaryInput = By.cssSelector("textarea[data-test='summary']");
    private final By submitButton = By.cssSelector("button[data-test='submit-button']");

    public void createNewIssue(String summaryText) {
        String originalWindow = driver.getWindowHandle();

        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(createButtonInHeader));
        createBtn.click();

        WebElement newIssueOption = wait.until(ExpectedConditions.elementToBeClickable(newIssueMenuItem));
        newIssueOption.click();

        switchToNewWindow(originalWindow);

        WebElement summaryField = wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInput));
        summaryField.sendKeys(summaryText);

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submit.click();
    }

}