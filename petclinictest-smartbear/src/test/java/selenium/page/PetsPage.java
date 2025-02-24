package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class PetsPage {

    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(name = "type")
    private WebElement typeSelectOne;

    @FindBy(className = "col-sm-offset-2")
    private WebElement colSmOffset;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Add Pet']")
    private WebElement addPetSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public PetsPage(WebDriver driver) {
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

    public void setNameText(String text) {
        waitFor(nameText).clear();
        nameText.sendKeys(text);
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


    public void clickAddPetSubmit() {
        click(addPetSubmit);
    }


}