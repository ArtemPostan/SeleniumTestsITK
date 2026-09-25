package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IssuesPage extends BasePage {
    public IssuesPage(WebDriver driver) {
        super(driver);
    }

    private final By createIssueButton = By.xpath("//a[@data-test='createIssueButton']");
    private final By summaryInput = By.xpath("//textarea[@data-test='summary']");
    private final By submitButton = By.xpath("//button[@data-test='submit-button']");

    public void createNewIssue(String summary) {
        clickCreateButton();
        typeSummary(summary);
        confirmCreation();
    }

    private void clickCreateButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createIssueButton)).click();
    }

    private void typeSummary(String summary) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInput)).sendKeys(summary);
    }

    private void confirmCreation() {
        driver.findElement(submitButton).click();
    }

}