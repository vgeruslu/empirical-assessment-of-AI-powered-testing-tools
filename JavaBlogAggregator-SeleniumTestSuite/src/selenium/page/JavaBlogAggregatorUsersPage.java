package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorUsersPage {

    @FindBy(id = "12")
    private WebElement submit;

    // @FindBy(xpath = "/descendant::button[normalize-space(.)='Delete']")
    @FindBy(xpath = "descendant::button[starts-with(normalize-space(.), \"Delete\")]")
    private WebElement deleteSubmit;

    // @FindBy(linkText = "Logout")
    @FindBy(linkText = "Sign Out")
    private WebElement logoutLink;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorUsersPage(WebDriver driver) {
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

    public void clickSubmit() {
        click(submit);
    }


    public void clickDeleteSubmit() {
        click(deleteSubmit);
    }


    public void clickLogoutLink() {
        click(logoutLink);
    }


}