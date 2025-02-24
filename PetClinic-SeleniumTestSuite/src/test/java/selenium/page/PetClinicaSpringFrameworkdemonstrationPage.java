package selenium.page;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class PetClinicaSpringFrameworkdemonstrationPage {


    // @FindBy(xpath = "/descendant::span[normalize-space(.)='Veterinarians']")
    @FindBy(xpath = "descendant::span[normalize-space(.)=\"Clinic Staff\"]")
    private WebElement veterinarians;
    @FindBy(xpath = "//body/div[1]/div/div[1]/div")
    private WebElement div;
    @FindBy(className = "col-12")
    private WebElement col;
    @FindBy(xpath = "/descendant::h2[normalize-space(.)='Welcome']")
    private WebElement welcome;

    @FindBy(className = "logo")
    private WebElement vmwareTanzuLogo;
    @FindBy(className = "col-md-12")
    private WebElement colMd;
    // @FindBy(linkText = "FIND OWNERS")
    @FindBy(xpath = "descendant::a[substring(normalize-space(.), string-length(normalize-space(.)) - 4) = \"wners\"]")
    private WebElement findOWNERSLink;

    @FindBy(className = "xd-container")
    private WebElement xdContainer;
    // @FindBy(xpath = "/descendant::span[normalize-space(.)='Find owners']")
    @FindBy(xpath = "descendant::span[substring(normalize-space(.), string-length(normalize-space(.)) - 4) = \"wners\"]")
    private WebElement findOwners;

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


    public void clickXdContainer() {
        click(xdContainer);
    }

    public void clickColMd() {
        click(colMd);
    }

    public void clickWelcome() {
        click(welcome);
    }


    public void clickVmwareTanzuLogo() {
        click(vmwareTanzuLogo);
    }

    public void clickCol() {
        click(col);
    }

    public void clickDiv() {
        click(div);
    }

    public void clickVeterinarians() {
        click(veterinarians);
    }


}