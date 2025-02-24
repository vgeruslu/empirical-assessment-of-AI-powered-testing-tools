package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorBlogformPage {


    @FindBy(xpath = "/descendant::label[normalize-space(.)='Name:']")
    private WebElement name;

    @FindBy(xpath = "/descendant::label[@class='col-sm-2 control-label'][2]")
    private WebElement label2;

    @FindBy(xpath = "/descendant::label[normalize-space(.)='Nick:']")
    private WebElement nick;
    @FindBy(xpath = "/descendant::label[@class='col-sm-2 control-label'][4]")
    private WebElement label;

    @FindBy(name = "url")
    private WebElement rssATOMURLText;

    @FindBy(name = "homepageUrl")
    private WebElement homepageURLText;
    @FindBy(name = "nick")
    private WebElement nickText;
    @FindBy(name = "shortName")
    private WebElement shortNameText;

    // @FindBy(linkText = "top java blogs")
    @FindBy(className = "navbar-brand")
    private WebElement topJavaBlogsLink;
    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(css = "input[value='Save']")
    private WebElement saveSubmit;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorBlogformPage(WebDriver driver) {
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


    public void clickSaveSubmit() {
        click(saveSubmit);
    }

    public void setShortNameText(String text) {
        waitFor(shortNameText).clear();
        shortNameText.sendKeys(text);
    }


    public void clickTopJavaBlogsLink() {
        click(topJavaBlogsLink);
    }

    public void setNickText(String text) {
        waitFor(nickText).clear();
        nickText.sendKeys(text);
    }

    public void clickLabel() {
        click(label);
    }


    public void setRssATOMURLText(String text) {
        waitFor(rssATOMURLText).clear();
        rssATOMURLText.sendKeys(text);
    }


    public void setHomepageURLText(String text) {
        waitFor(homepageURLText).clear();
        homepageURLText.sendKeys(text);
    }

    public void clickName() {
        click(name);
    }


    public void clickLabel2() {
        click(label2);
    }


    public void clickNick() {
        click(nick);
    }


}