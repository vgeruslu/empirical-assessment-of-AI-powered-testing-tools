package selenium.page;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class OwnersPage {


    @FindBy(linkText = "Edit Pet")
    private WebElement editPetLink;

    @FindBy(xpath = "/descendant::th[normalize-space(.)='Telephone']")
    private WebElement telephone;
    @FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Name Jewel Birth Date 2010-03-07 Type dog\"]]/td[2]/table/tbody/tr[1]/td[count(ancestor::table//th[.=\"Visit Date\"]/preceding-sibling::th)+1]/a")
    private WebElement link;
    @FindBy(linkText = "Peter McTavish")
    private WebElement peterMcTavishLink;
    @FindBy(linkText = "Harold Davis")
    private WebElement haroldDavisLink;

    @FindBy(xpath = "/descendant::td[normalize-space(.)='123 Happy Lane']")
    private WebElement happyLane;
    @FindBy(linkText = "Betty Davis")
    private WebElement bettyDavisLink;

    @FindBy(xpath = "/descendant::td[normalize-space(.)='6085551749']")
    private WebElement td2;
    @FindBy(linkText = "George Franklin")
    private WebElement georgeFranklinLink;

    @FindBy(linkText = "Edit Owner")
    private WebElement editOwnerLink;

    @FindBy(linkText = "Jason Franklin")
    private WebElement jasonFranklinLink;
    @FindBy(linkText = "Add Visit")
    private WebElement addVisitLink;

    @FindBy(xpath = "//table/tbody/tr[1]/td[2]")
    private WebElement td;
    @FindBy(className = "xd-container")
    private WebElement xdContainer;

    @FindBy(xpath = "/descendant::h2[normalize-space(.)='Pets and Visits']")
    private WebElement petsAndVisits;

    @FindBy(linkText = "HOME")
    private WebElement homeLink;

    @FindBy(linkText = "Nithin Joy")
    private WebElement nithinJoyLink;
    @FindBy(linkText = "Eduardo Rodriquez")
    private WebElement eduardoRodriquezLink;

    // @FindBy(linkText = "Add New Pet")
    @FindBy(linkText = "Register Pet")
    private WebElement addNewPetLink;
    @FindBy(name = "firstName")
    private WebElement firstNameText;

    @FindBy(name = "lastName")
    private WebElement lastNameText;

    @FindBy(name = "address")
    private WebElement addressText;

    @FindBy(name = "city")
    private WebElement cityText;

    @FindBy(name = "telephone")
    private WebElement telephoneText;

    @FindBy(xpath = "/descendant::button[normalize-space(.)='Add Owner']")
    private WebElement addOwnerSubmit;

    @FindBy(xpath = "/descendant::span[normalize-space(.)='Home']")
    private WebElement home;

    private WebDriver driver;

    private static final Duration DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = Duration.ofSeconds(15);

    public OwnersPage(WebDriver driver) {
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

    public void setFirstNameText(String text) {
        waitFor(firstNameText).clear();
        firstNameText.sendKeys(text);
    }


    public void setLastNameText(String text) {
        waitFor(lastNameText).clear();
        lastNameText.sendKeys(text);
    }


    public void setAddressText(String text) {
        waitFor(addressText).clear();
        addressText.sendKeys(text);
    }


    public void setCityText(String text) {
        waitFor(cityText).clear();
        cityText.sendKeys(text);
    }


    public void setTelephoneText(String text) {
        waitFor(telephoneText).clear();
        telephoneText.sendKeys(text);
    }


    public void clickAddOwnerSubmit() {
        click(addOwnerSubmit);
    }


    public void clickHome() {
        click(home);
    }

    public void clickEduardoRodriquezLink() {
        click(eduardoRodriquezLink);
    }


    public void clickAddNewPetLink() {
        click(addNewPetLink);
    }

    public void clickXdContainer() {
        click(xdContainer);
    }


    public void clickPetsAndVisits() {
        click(petsAndVisits);
    }


    public void clickHomeLink() {
        click(homeLink);
    }


    public void clickNithinJoyLink() {
        click(nithinJoyLink);
    }

    public void clickAddVisitLink() {
        click(addVisitLink);
    }


    public void clickTd() {
        click(td);
    }

    public void clickGeorgeFranklinLink() {
        click(georgeFranklinLink);
    }


    public void clickEditOwnerLink() {
        click(editOwnerLink);
    }


    public void clickJasonFranklinLink() {
        click(jasonFranklinLink);
    }

    public void clickBettyDavisLink() {
        click(bettyDavisLink);
    }


    public void clickTd2() {
        click(td2);
    }

    public void clickHaroldDavisLink() {
        click(haroldDavisLink);
    }


    public void clickHappyLane() {
        click(happyLane);
    }

    public void clickPeterMcTavishLink() {
        click(peterMcTavishLink);
    }

    public void clickLink() {
        click(link);
    }

    public void clickEditPetLink() {
        click(editPetLink);
    }


    public void clickTelephone() {
        click(telephone);
    }


}