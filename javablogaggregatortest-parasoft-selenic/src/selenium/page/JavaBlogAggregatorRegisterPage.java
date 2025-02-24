package selenium.page;


import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorRegisterPage {


    @FindBy(xpath = "/descendant::div[@class='col-sm-2'][1]")
    private WebElement div2;
    @FindBy(xpath = "/descendant::div[@class='form-group'][2]")
    private WebElement div;
    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(name = "email")
    private WebElement emailText;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "password_again")
    private WebElement passwordAgainPassword;

    //@FindBy(css = "input[value='Save']")
    @FindBy(css = "input[value^='Save']")
    private WebElement saveSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorRegisterPage(WebDriver driver) {
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


    public void setEmailText(String text) {
        waitFor(emailText).clear();
        emailText.sendKeys(text);
    }


    public void setPassword(String text) {
        waitFor(password).clear();
        password.sendKeys(text);
    }


    public void setPasswordAgainPassword(String text) {
        waitFor(passwordAgainPassword).clear();
        passwordAgainPassword.sendKeys(text);
    }


    public void clickSaveSubmit() {
        click(saveSubmit);
    }

    public void clickDiv() {
        click(div);
    }

    public void clickDiv2() {
        click(div2);
    }


}