package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class PetClinicaSpringFrameworkdemonstrationPage {


    @FindBy(xpath = "/descendant::h2[normalize-space(.)='Welcome']")
    private WebElement welcome;

    // @FindBy(xpath = "/descendant::span[normalize-space(.)='Veterinarians']")
    @FindBy(xpath = "descendant::span[normalize-space(.)=\"Clinic Staff\"]")
    private WebElement veterinarians;
    @FindBy(className = "logo")
    private WebElement vmwareTanzuLogo;
    @FindBy(className = "col-12")
    private WebElement col;
    @FindBy(linkText = "FIND OWNERS")
    private WebElement findOWNERSLink;

    @FindBy(className = "col-md-12")
    private WebElement colMd;
    @FindBy(xpath = "/descendant::span[normalize-space(.)='Find owners']")
    private WebElement findOwners;

    @FindBy(className = "xd-container")
    private WebElement xdContainer;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public PetClinicaSpringFrameworkdemonstrationPage(WebDriver driver) {
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

    public void clickFindOwners() {
        click(findOwners);
    }

    public void clickFindOWNERSLink() {
        click(findOWNERSLink);
    }


    public void clickColMd() {
        click(colMd);
    }


    public void clickXdContainer() {
        click(xdContainer);
    }

    public void clickCol() {
        click(col);
    }

    public void clickVmwareTanzuLogo() {
        click(vmwareTanzuLogo);
    }

    public void clickWelcome() {
        click(welcome);
    }


    public void clickVeterinarians() {
        click(veterinarians);
    }


}