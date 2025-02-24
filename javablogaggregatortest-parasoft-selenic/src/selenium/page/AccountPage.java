package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AccountPage {


    // @FindBy(linkText = "Logout")
    @FindBy(linkText = "Sign Out")
    private WebElement logoutLink;
    @FindBy(linkText = "Blogs")
    private WebElement blogsLink;
    // @FindBy(linkText = "edit")
    @FindBy(partialLinkText = "dit")
    private WebElement editLink;
    // @FindBy(linkText = "top java blogs")
    @FindBy(className = "navbar-brand")
    private WebElement topJavaBlogsLink;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public AccountPage(WebDriver driver) {
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

    public void clickTopJavaBlogsLink() {
        click(topJavaBlogsLink);
    }

    public void clickEditLink() {
        click(editLink);
    }

    public void clickBlogsLink() {
        click(blogsLink);
    }

    public void clickLogoutLink() {
        click(logoutLink);
    }


}