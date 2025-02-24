package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class VisitsPage {

    @FindBy(xpath = "/descendant::th[normalize-space(.)='Date']")
    private WebElement date;

    @FindBy(xpath = "/descendant::label[normalize-space(.)='Description']")
    private WebElement description;

    @FindBy(name = "description")
    private WebElement descriptionText;

    @FindBy(className = "col-sm-offset-2")
    private WebElement colSmOffset;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Add Visit']")
    private WebElement addVisitSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public VisitsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private WebElement waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(elementToBeClickable(element));
    }

    private WebElement scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
        return element;
    }

    protected WebElement click(WebElement element) {
        WebElement webElement = scrollTo(waitFor(element));
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        return wait.ignoring(ElementClickInterceptedException.class).until(webDriver -> {
            webElement.click();
            return webElement;
        });
    }

    public void clickDate() {
        click(date);
    }


    public void clickDescription() {
        click(description);
    }


    public void setDescriptionText(String text) {
        waitFor(descriptionText).clear();
        descriptionText.sendKeys(text);
    }


    public void clickColSmOffset() {
        click(colSmOffset);
    }


    public void clickAddVisitSubmit() {
        click(addVisitSubmit);
    }


}