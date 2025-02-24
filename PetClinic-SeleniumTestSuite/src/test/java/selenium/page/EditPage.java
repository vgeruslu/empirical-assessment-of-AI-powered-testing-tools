package selenium.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class EditPage {


    @FindBy(className = "col-sm-offset-2")
    private WebElement colSmOffset;
    @FindBy(name = "type")
    private WebElement typeSelectOne;
    @FindBy(xpath = "/descendant::div[normalize-space(.)='bird cat dog hamster lizard snake']")
    private WebElement birdCatDogHamsterLizardSnake;
    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Update Pet']")
    private WebElement updatePetSubmit;
    @FindBy(name = "telephone")
    private WebElement telephoneText;

    @FindBy(className = "xd-container")
    private WebElement xdContainer;
    @FindBy(name = "lastName")
    private WebElement lastNameText;
    @FindBy(name = "firstName")
    private WebElement firstNameText;
    @FindBy(name = "address")
    private WebElement addressText;
    @FindBy(name = "city")
    private WebElement cityText;

    //@FindBy(xpath = "/descendant::button[normalize-space(.)='Update Owner']")
    @FindBy(xpath = "descendant::button[normalize-space(.)=\"Update Owner\"]")
    private WebElement updateOwnerSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public EditPage(WebDriver driver) {
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

    public void setCityText(String text) {
        waitFor(cityText).clear();
        cityText.sendKeys(text);
    }


    public void clickUpdateOwnerSubmit() {
        click(updateOwnerSubmit);
    }

    public void setAddressText(String text) {
        waitFor(addressText).clear();
        addressText.sendKeys(text);
    }

    public void setFirstNameText(String text) {
        waitFor(firstNameText).clear();
        firstNameText.sendKeys(text);
    }

    public void setLastNameText(String text) {
        waitFor(lastNameText).clear();
        lastNameText.sendKeys(text);
    }

    public void setTelephoneText(String text) {
        waitFor(telephoneText).clear();
        telephoneText.sendKeys(text);
    }


    public void clickXdContainer() {
        click(xdContainer);
    }

    public void setNameText(String text) {
        waitFor(nameText).clear();
        nameText.sendKeys(text);
    }


    public void clickUpdatePetSubmit() {
        click(updatePetSubmit);
    }

    public void clickBirdCatDogHamsterLizardSnake() {
        click(birdCatDogHamsterLizardSnake);
    }

    public void selectTypeSelectOne(String text) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(webdriver -> new Select(typeSelectOne).getOptions().stream()
                .anyMatch(element -> text.equals(element.getText())));
        Select dropdown = new Select(typeSelectOne);
        dropdown.selectByVisibleText(text);
    }

    public void clickColSmOffset() {
        click(colSmOffset);
    }


}