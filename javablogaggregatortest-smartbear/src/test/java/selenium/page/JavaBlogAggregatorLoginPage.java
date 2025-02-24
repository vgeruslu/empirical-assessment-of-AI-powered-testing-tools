package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorLoginPage {

    @FindBy(className = "form-signin")
    private WebElement formSigninForm;

    @FindBy(name = "j_username")
    private WebElement jUsernameText;

    @FindBy(name = "j_password")
    private WebElement jPasswordPassword;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Sign in']")
    private WebElement signInSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorLoginPage(WebDriver driver) {
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

    public void setJUsernameText(String text) {
        waitFor(jUsernameText).clear();
        jUsernameText.sendKeys(text);
    }


    public void setJPasswordPassword(String text) {
        waitFor(jPasswordPassword).clear();
        jPasswordPassword.sendKeys(text);
    }


    public void clickSignInSubmit() {
        click(signInSubmit);
    }


    public void clickFormSigninForm() {
        click(formSigninForm);
    }
}