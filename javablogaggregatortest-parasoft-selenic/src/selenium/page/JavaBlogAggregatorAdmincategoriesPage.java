package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorAdmincategoriesPage {

    @FindBy(xpath = "/descendant::button[normalize-space(.)='New category']")
    private WebElement newCategorySubmit;

    @FindBy(name = "name")
    private WebElement nameText;

    @FindBy(name = "shortName")
    private WebElement shortNameText;

    @FindBy(css = "input[value='Save']")
    private WebElement saveSubmit;

    @FindBy(linkText = "Blogs")
    private WebElement blogsLink;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorAdmincategoriesPage(WebDriver driver) {
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

    public void clickNewCategorySubmit() {
        click(newCategorySubmit);
    }


    public void setNameText(String text) {
        waitFor(nameText).clear();
        nameText.sendKeys(text);
    }


    public void setShortNameText(String text) {
        waitFor(shortNameText).clear();
        shortNameText.sendKeys(text);
    }


    public void clickSaveSubmit() {
        click(saveSubmit);
    }


    public void clickBlogsLink() {
        click(blogsLink);
    }


}