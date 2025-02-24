package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class FindPage {


    @FindBy(className = "col-sm-offset-2")
    private WebElement colSmOffset;
    @FindBy(className = "fa-home")
    private WebElement faHome;

    @FindBy(className = "xd-container")
    private WebElement xdContainer;

    @FindBy(name = "lastName")
    private WebElement lastNameText;
    //@FindBy(xpath = "/descendant::button[normalize-space(.)='Find Owner']")
    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'btn-primary')]")
    private WebElement findOwnerSubmit;

    @FindBy(xpath = "/descendant::span[normalize-space(.)='Home']")
    private WebElement home;
    @FindBy(linkText = "Add Owner")
    private WebElement addOwnerLink;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public FindPage(WebDriver driver) {
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

    public void clickAddOwnerLink() {
        click(addOwnerLink);
    }

    public void clickFindOwnerSubmit() {
        click(findOwnerSubmit);
    }

    public void clickFaHome() {
        click(faHome);
    }


    public void clickXdContainer() {
        click(xdContainer);
    }


    public void setLastNameText(String text) {
        waitFor(lastNameText).clear();
        lastNameText.sendKeys(text);
    }


    public void clickHome() {
        click(home);
    }

    public void clickColSmOffset() {
        click(colSmOffset);
    }


}