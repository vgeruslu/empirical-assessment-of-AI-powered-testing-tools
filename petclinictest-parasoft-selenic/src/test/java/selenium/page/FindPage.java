package selenium.page;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class FindPage {


    @FindBy(className = "fa-home")
    private WebElement faHome;
    @FindBy(linkText = "FIND OWNERS")
    private WebElement findOWNERSLink;

    @FindBy(id = "lastNameGroup")
    private WebElement lastNameGroup;
    @FindBy(className = "xd-container")
    private WebElement xdContainer;
    @FindBy(linkText = "HOME")
    private WebElement homeLink;

    @FindBy(xpath = "/descendant::span[normalize-space(.)='Find owners']")
    private WebElement findOwners;

    @FindBy(className = "col-sm-offset-2")
    private WebElement colSmOffset;
    @FindBy(id = "search-owner-form")
    private WebElement searchOwnerForm;

    @FindBy(xpath = "/descendant::span[normalize-space(.)='Home']")
    private WebElement home;

    @FindBy(name = "lastName")
    private WebElement lastNameText;

    // @FindBy(xpath = "/descendant::button[normalize-space(.)='Find Owner']")
    @FindBy(css = "[type='submit']")
    private WebElement findOwnerSubmit;
    //@FindBy(linkText = "Add Owner")
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

    public void clickSearchOwnerForm() {
        click(searchOwnerForm);
    }


    public void clickHome() {
        click(home);
    }


    public void setLastNameText(String text) {
        waitFor(lastNameText).clear();
        lastNameText.sendKeys(text);
    }


    public void clickFindOwnerSubmit() {
        click(findOwnerSubmit);
    }

    public void clickHomeLink() {
        click(homeLink);
    }


    public void clickFindOwners() {
        click(findOwners);
    }


    public void clickColSmOffset() {
        click(colSmOffset);
    }

    public void clickXdContainer() {
        click(xdContainer);
    }

    public void clickFindOWNERSLink() {
        click(findOWNERSLink);
    }


    public void clickLastNameGroup() {
        click(lastNameGroup);
    }

    public void clickFaHome() {
        click(faHome);
    }


}