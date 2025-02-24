package selenium.page;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class OwnersPage {


    @FindBy(xpath = "/descendant::th[normalize-space(.)='Telephone']")
    private WebElement telephone;
    @FindBy(className = "col-12")
    private WebElement col;

    @FindBy(className = "fa-fast-backward")
    private WebElement faFastBackward;

    @FindBy(xpath = "//body/div[1]/div/div[1]")
    private WebElement div;
    @FindBy(xpath = "/descendant::dt[normalize-space(.)='Birth Date']")
    private WebElement birthDate;
    @FindBy(xpath = "/descendant::td[normalize-space(.)='6085555387']")
    private WebElement td4;

    @FindBy(xpath = "/descendant::dd[normalize-space(.)='2011-08-10']")
    private WebElement dd;
    @FindBy(linkText = "Eduardo Rodriquez")
    private WebElement eduardoRodriquezLink;

    @FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Name Jewel Birth Date 2010-03-07 Type dog\"]]/td[2]/table/tbody/tr[1]/td[count(ancestor::table//th[.=\"Visit Date\"]/preceding-sibling::th)+1]/a")
    private WebElement link;
    @FindBy(linkText = "Peter McTavish")
    private WebElement peterMcTavishLink;
    @FindBy(linkText = "Jean Coleman")
    private WebElement jeanColemanLink;
    @FindBy(linkText = "Harold Davis")
    private WebElement haroldDavisLink;

    @FindBy(xpath = "/descendant::td[normalize-space(.)='123 Happy Lane']")
    private WebElement happyLane;
    @FindBy(xpath = "/descendant::td[normalize-space(.)='6085551749']")
    private WebElement td3;
    @FindBy(linkText = "George Franklin")
    private WebElement georgeFranklinLink;

    @FindBy(linkText = "Jason Franklin")
    private WebElement jasonFranklinLink;
    @FindBy(linkText = "Betty Davis")
    private WebElement bettyDavisLink;

    @FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Name Basil Birth Date 2012-08-06 Type hamster\"]]/td[2]")
    private WebElement td2;
    // @FindBy(linkText = "Add Visit")
    @FindBy(linkText = "Book an Appointment")
    private WebElement addVisitLink;

    @FindBy(xpath = "//table/tbody/tr[1]/td[2]")
    private WebElement td;
    @FindBy(linkText = "Jeff Black")
    private WebElement jeffBlackLink;
    @FindBy(linkText = "Nithin Joy")
    private WebElement nithinJoyLink;
    // @FindBy(linkText = "Edit Pet")
    @FindBy(linkText = "Alter Pet Details")
    private WebElement editPetLink;
    @FindBy(xpath = "/descendant::label[normalize-space(.)='First Name']")
    private WebElement firstName;

    //@FindBy(css = "a[class='btn btn-primary']")
    //@FindBy(id = "editLink")
    @FindBy(linkText = "Edit Owner")
    private WebElement editOwnerLink;
    @FindBy(className = "xd-container")
    private WebElement xdContainer;

    @FindBy(className = "col-sm-offset-2")
    private WebElement colSmOffset;

    // @FindBy(linkText = "Add New Pet")
    @FindBy(linkText = "Register Pet")
    private WebElement addNewPetLink;

    @FindBy(linkText = "HOME")
    private WebElement homeLink;

    @FindBy(xpath = "/descendant::h2[normalize-space(.)='Pets and Visits']")
    private WebElement petsAndVisits;

    @FindBy(xpath = "/descendant::dd[normalize-space(.)='Alen']")
    private WebElement alen;
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

    //@FindBy(xpath = "/descendant::button[normalize-space(.)='Add Owner']")
    @FindBy(xpath = "descendant::button[normalize-space(.)=\"Add Owner\"]")
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

    public void clickXdContainer() {
        click(xdContainer);
    }


    public void clickColSmOffset() {
        click(colSmOffset);
    }


    public void clickAddNewPetLink() {
        click(addNewPetLink);
    }


    public void clickHomeLink() {
        click(homeLink);
    }


    public void clickPetsAndVisits() {
        click(petsAndVisits);
    }


    public void clickAlen() {
        click(alen);
    }

    public void clickFirstName() {
        click(firstName);
    }


    public void clickEditOwnerLink() {
        click(editOwnerLink);
    }

    public void clickEditPetLink() {
        click(editPetLink);
    }

    public void clickNithinJoyLink() {
        click(nithinJoyLink);
    }

    public void clickJeffBlackLink() {
        click(jeffBlackLink);
    }

    public void clickAddVisitLink() {
        click(addVisitLink);
    }


    public void clickTd() {
        click(td);
    }

    public void clickBettyDavisLink() {
        click(bettyDavisLink);
    }


    public void clickTd2() {
        click(td2);
    }

    public void clickGeorgeFranklinLink() {
        click(georgeFranklinLink);
    }


    public void clickJasonFranklinLink() {
        click(jasonFranklinLink);
    }

    public void clickTd3() {
        click(td3);
    }

    public void clickHaroldDavisLink() {
        click(haroldDavisLink);
    }


    public void clickHappyLane() {
        click(happyLane);
    }

    public void clickJeanColemanLink() {
        click(jeanColemanLink);
    }

    public void clickPeterMcTavishLink() {
        click(peterMcTavishLink);
    }

    public void clickEduardoRodriquezLink() {
        click(eduardoRodriquezLink);
    }


    public void clickLink() {
        click(link);
    }

    public void clickTd4() {
        click(td4);
    }


    public void clickDd() {
        click(dd);
    }

    public void clickBirthDate() {
        click(birthDate);
    }

    public void clickCol() {
        click(col);
    }


    public void clickFaFastBackward() {
        click(faFastBackward);
    }


    public void clickDiv() {
        click(div);
    }

    public void clickTelephone() {
        click(telephone);
    }


}