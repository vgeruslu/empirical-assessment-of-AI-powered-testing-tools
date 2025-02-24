package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorPage {


    @FindBy(linkText = "Add news")
    private WebElement addNewsLink;
    @FindBy(linkText = "Categories")
    private WebElement categoriesLink;
    @FindBy(linkText = "Login")
    private WebElement loginLink;

    @FindBy(linkText = "Admin detail")
    private WebElement adminDetailLink;
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(linkText = "Blogs")
    private WebElement blogsLink;
    @FindBy(linkText = "Administer")
    private WebElement administerButton;

    @FindBy(linkText = "Users")
    private WebElement usersLink;
    @FindBy(linkText = "News")
    private WebElement newsLink;
    @FindBy(linkText = "My account")
    private WebElement myAccountLink;
    //@FindBy(linkText = "Register")
    @FindBy(linkText = "Make an Account")
    private WebElement registerLink;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorPage(WebDriver driver) {
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

    public void clickRegisterLink() {
        click(registerLink);
    }

    public void clickMyAccountLink() {
        click(myAccountLink);
    }

    public void clickNewsLink() {
        click(newsLink);
    }

    public void clickAdministerButton() {
        click(administerButton);
    }


    public void clickUsersLink() {
        click(usersLink);
    }

    public void clickLogoutLink() {
        click(logoutLink);
    }


    public void clickBlogsLink() {
        click(blogsLink);
    }

    public void clickLoginLink() {
        click(loginLink);
    }


    public void clickAdminDetailLink() {
        click(adminDetailLink);
    }

    public void clickCategoriesLink() {
        click(categoriesLink);
    }

    public void clickAddNewsLink() {
        click(addNewsLink);
    }


}