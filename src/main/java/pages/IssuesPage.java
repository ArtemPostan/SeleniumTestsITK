package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class IssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By createButtonInHeader = By.xpath("//span[text()='Создать']/ancestor::button");
    private final By newIssueMenuItem = By.cssSelector("[href*='newIssue']");
    private final By summaryInput = By.cssSelector("textarea[data-test='summary']");
    private final By submitButton = By.cssSelector("button[data-test='submit-button']");

    public IssuesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void createNewIssue(String summaryText) {
        String originalWindow = driver.getWindowHandle();

        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(createButtonInHeader));
        createBtn.click();

        WebElement newIssueOption = wait.until(ExpectedConditions.elementToBeClickable(newIssueMenuItem));
        newIssueOption.click();

        wait.until(driver -> driver.getWindowHandles().size() > 1);
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement summaryField = wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInput));
        summaryField.sendKeys(summaryText);

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submit.click();
    }

}