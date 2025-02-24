package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorMyaccountPage {


    @FindBy(linkText = "News")
    private WebElement newsLink;
    @FindBy(className = "modal-body")
    private WebElement modalBody;
    @FindBy(linkText = "Logout")
    private WebElement logoutLink;
    @FindBy(xpath = "/descendant::button[normalize-space(.)='remove']")
    private WebElement removeSubmit;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Delete']")
    private WebElement deleteSubmit;

    @FindBy(linkText = "main java blogs")
    private WebElement topJavaBlogsLink;
    @FindBy(linkText = "Steps to build a Spring-boot application")
    private WebElement stepsToBuildASpringBootApplicationLink;
    @FindBy(linkText = "edit")
    private WebElement editLink;
    @FindBy(xpath = "/descendant::button[normalize-space(.)='New blog']")
    private WebElement newBlogSubmit;

    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(name = "shortName")
    private WebElement shortNameText;

    @FindBy(name = "nick")
    private WebElement nickText;

    @FindBy(name = "homepageUrl")
    private WebElement homepageURLText;

    @FindBy(name = "url")
    private WebElement rssATOMURLText;

    @FindBy(xpath = "/descendant::div[normalize-space(.)='Close']")
    private WebElement close;

    @FindBy(css = "input[value='Save']")
    private WebElement saveSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorMyaccountPage(WebDriver driver) {
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

    public void clickNewBlogSubmit() {
        click(newBlogSubmit);
    }


    public void setNameText(String text) {
        waitFor(nameText).clear();
        nameText.sendKeys(text);
    }


    public void setShortNameText(String text) {
        waitFor(shortNameText).clear();
        shortNameText.sendKeys(text);
    }


    public void setNickText(String text) {
        waitFor(nickText).clear();
        nickText.sendKeys(text);
    }


    public void setHomepageURLText(String text) {
        waitFor(homepageURLText).clear();
        homepageURLText.sendKeys(text);
    }


    public void setRssATOMURLText(String text) {
        waitFor(rssATOMURLText).clear();
        rssATOMURLText.sendKeys(text);
    }


    public void clickClose() {
        click(close);
    }


    public void clickSaveSubmit() {
        click(saveSubmit);
    }

    public void clickEditLink() {
        click(editLink);
    }

    public void clickStepsToBuildASpringBootApplicationLink() {
        click(stepsToBuildASpringBootApplicationLink);
    }

    public void clickRemoveSubmit() {
        click(removeSubmit);
    }


    public void clickDeleteSubmit() {
        click(deleteSubmit);
    }


    public void clickTopJavaBlogsLink() {
        click(topJavaBlogsLink);
    }

    public void clickLogoutLink() {
        click(logoutLink);
    }

    public void clickModalBody() {
        click(modalBody);
    }

    public void clickNewsLink() {
        click(newsLink);
    }


}