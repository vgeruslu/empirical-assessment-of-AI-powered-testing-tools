package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class JavaBlogAggregatorBlogsPage {


    // @FindBy(xpath = "//table/tbody/tr[td[count(ancestor::table//th[.=\"blog\"]/preceding-sibling::th)+1][normalize-space()=\"Java Deep (Java Deep: Pure Java, nothing else)\"]]/td[count(ancestor::table//th[.=\"edit\"]/preceding-sibling::th)+1]/a")
    @FindBy(xpath = "descendant::a[normalize-space(.)=\"edit\"][1]")
    private WebElement link;

    @FindBy(xpath = "//table/tbody/tr[td[count(ancestor::table//th[.=\"blog\"]/preceding-sibling::th)+1][normalize-space()=\"Java Blog (The Java Blog: Thoughts, tips and tricks about the Java programming language)\"]]/td[count(ancestor::table//th[.=\"edit\"]/preceding-sibling::th)+1]/select")
    private WebElement selectOne2;

    @FindBy(linkText = "My account")
    private WebElement myAccountLink;
    @FindBy(xpath = "//table/tbody/tr[td[count(ancestor::table//th[.=\"blog\"]/preceding-sibling::th)+1][normalize-space()=\"Spring-Clean (Spring-Clean Your Blog in Five Easy Steps)\"]]/td[count(ancestor::table//th[.=\"edit\"]/preceding-sibling::th)+1]/select")
    private WebElement selectOne;
    // @FindBy(linkText = "top java blogs")
    @FindBy(className = "navbar-brand")
    private WebElement topJavaBlogsLink;

    @FindBy(linkText = "Administer")
    private WebElement administerButton;

    @FindBy(xpath = "//table/tbody/tr[td[count(ancestor::table//th[.=\"blog\"]/preceding-sibling::th)+1][normalize-space()=\"Spring (Spring Fundamentals - A Quick Walkthrough)\"]]/td[count(ancestor::table//th[.=\"edit\"]/preceding-sibling::th)+1]/button")
    private WebElement submit;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Delete']")
    private WebElement deleteSubmit;

    // @FindBy(linkText = "Logout")
    @FindBy(linkText = "Sign Out")
    private WebElement logoutLink;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public JavaBlogAggregatorBlogsPage(WebDriver driver) {
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


    public void clickAdministerButton() {
        click(administerButton);
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

    public void selectSelectOne(String text) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(webdriver -> new Select(selectOne).getOptions().stream()
                .anyMatch(element -> text.equals(element.getText())));
        Select dropdown = new Select(selectOne);
        dropdown.selectByVisibleText(text);
    }

    public void clickLink() {
        click(link);
    }


    public void selectSelectOne2(String text) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(webdriver -> new Select(selectOne2).getOptions().stream()
                .anyMatch(element -> text.equals(element.getText())));
        Select dropdown = new Select(selectOne2);
        dropdown.selectByVisibleText(text);
    }


    public void clickMyAccountLink() {
        click(myAccountLink);
    }


}