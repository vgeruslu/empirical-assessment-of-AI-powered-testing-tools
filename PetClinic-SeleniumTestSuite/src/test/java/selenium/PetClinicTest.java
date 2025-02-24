package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.page.*;
import java.util.HashMap;
import java.util.Map;

public class PetClinicTest {

    private static final String BASE_URL = "http://localhost:8080";

    private WebDriver driver;

    // Method executed before each test method, WebDriver uses JUnit 5 annotation @BeforeEach
    @BeforeEach
    public void beforeTest() {
        ChromeOptions opts = new ChromeOptions(); // Configures ChromeDriver Behaviour
        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("profile.managed_default_content_settings.geolocation", 2); // Disables geolocations
        prefs.put("profile.default_content_setting_values.notifications", 2); // Disables notifications
        opts.setExperimentalOption("prefs", prefs); // Apply preferences to ChromeOptions

        // Customizing browser behaviour
        opts.addArguments("--start-maximized"); // Maximise browser window
        opts.addArguments("--incognito"); // Enables incognito
        opts.addArguments("--enable-strict-powerful-feature-restrictions"); // Feature restrictions
        driver = new ChromeDriver(opts); // Initialise new Chrome driver
        driver.manage().window().maximize();
    }

    // Method executed after each test method, WebDriver uses JUnit 5 annotation @AfterEach
    @AfterEach
    public void afterTest() {
        if (driver != null) {
            driver.quit(); // Closes browser windows
        }
    }

    /**
     * Description: Testing adding an owner to the pet clinic
     */
    @Test
    public void testAddOwner() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickAddOwnerLink();
        OwnersPage ownersPage = new OwnersPage(driver);

        createOwner("John", "Smith", "123 Happy Lane", "London", "1234567890", ownersPage);

        ownersPage.clickHome();
    }

    /**
     * Description: This test adds a pet to an owner
     */
    @Test
    public void testAddPet() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickEduardoRodriquezLink();
        ownersPage.clickAddNewPetLink();

        PetsPage petsPage = new PetsPage(driver);
        petsPage.setNameText("Jimmy");
        petsPage.selectTypeSelectOne("snake");
        petsPage.clickColSmOffset();
        petsPage.clickAddPetSubmit();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();
        findPage.clickFindOwnerSubmit();
        ownersPage.clickEduardoRodriquezLink();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
    }

    /**
     * Description: This test finds an owner which has been added to the Pet Clinic, using the surname of the owner.
     */
    @Test
    public void testFindOwnerUsingSurname() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();
        findPage.clickAddOwnerLink();

        OwnersPage ownersPage = new OwnersPage(driver);

        createOwner("John", "Smith", "123 Happy Lane", "London", "1234567890", ownersPage);

        ownersPage.clickHomeLink();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();
        findPage.setLastNameText("Smith");
        findPage.clickFindOwnerSubmit();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickXdContainer();
    }

    /**
     * Description: This test finds an owner which has the same surname as another owner. When searching for the
     * surname, the software presents us with a list of the owners which has the same surname.
     */
    @Test
    public void testFindOwnerWithSameSurnameAsSomeoneElse() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();

        FindPage findPage = new FindPage(driver);
        findPage.clickFaHome();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();
        findPage.clickXdContainer();
        findPage.clickAddOwnerLink();

        OwnersPage ownersPage = new OwnersPage(driver);

        createOwner("Nithin", "Joy", "1 India Street", "Belfast", "1234567890", ownersPage);

        ownersPage.clickHomeLink();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();
        findPage.clickAddOwnerLink();

        createOwner("Joseph", "Joy", "2 India Street", "Belfast", "1234567890", ownersPage);

        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();
        findPage.setLastNameText("Joy");
        findPage.clickFindOwnerSubmit();
        ownersPage.clickNithinJoyLink();
        ownersPage.clickHome();
    }

    /**
     * Description: This test manually finds the owner, but not searching the surname and by clicking on the find
     * owner button which shows the list of owners present in the pet clinic. I am trying to find the owner Jeff Black.
     */
    @Test
    public void testFindOwnerManually() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickEduardoRodriquezLink();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
    }

    /**
     * Description: This test selects a pet and adds a visit for the pet.
     */
    @Test
    public void testAddingVisit() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickXdContainer();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();

        FindPage findPage = new FindPage(driver);
        findPage.clickXdContainer();
        findPage.setLastNameText("Black");
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickAddVisitLink();

        VisitsPage visitsPage = new VisitsPage(driver);
        visitsPage.clickDate();
        visitsPage.clickDescription();
        visitsPage.setDescriptionText("Lucky is limping when walking.");
        visitsPage.clickColSmOffset();
        visitsPage.clickAddVisitSubmit();
        ownersPage.clickHomeLink();
    }

    /**
     * Description: This test changes the first name of the owner.
     */
    @Test
    public void testEditOwnerFirstName() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickGeorgeFranklinLink();
        ownersPage.clickEditOwnerLink();

        EditPage editPage = new EditPage(driver);
        editPage.setFirstNameText("Jason");
        editPage.clickUpdateOwnerSubmit();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();
        findPage.clickFindOwnerSubmit();
        ownersPage.clickJasonFranklinLink();
        ownersPage.clickHome();
    }

    /**
     * Description: This test changes the last name of an owner.
     */
    @Test
    public void testEditOwnerLastName() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickBettyDavisLink();
        ownersPage.clickEditOwnerLink();

        EditPage editPage = new EditPage(driver);
        editPage.setLastNameText("David");
        editPage.clickUpdateOwnerSubmit();
        ownersPage.clickPetsAndVisits();
        ownersPage.clickHomeLink();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();
        findPage.setLastNameText("David");
        findPage.clickFindOwnerSubmit();
        ownersPage.clickTd3();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
    }

    /**
     * Description: This test changes the address of an owner.
     */
    @Test
    public void testEditOwnerAddress() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickHaroldDavisLink();
        ownersPage.clickEditOwnerLink();

        EditPage editPage = new EditPage(driver);
        editPage.setAddressText("123 Happy Lane");
        editPage.clickXdContainer();
        editPage.clickUpdateOwnerSubmit();
        ownersPage.clickHome();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();
        findPage.setLastNameText("Davis");
        findPage.clickColSmOffset();
        findPage.clickFindOwnerSubmit();
        ownersPage.clickHappyLane();
        ownersPage.clickHomeLink();
        petClinicaSpringFrameworkdemonstrationPage.clickCol();
    }

    /**
     * Description: This test changes the city of the owner.
     */
    @Test
    public void testEditOwnerCity() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickPeterMcTavishLink();
        ownersPage.clickEditOwnerLink();

        EditPage editPage = new EditPage(driver);
        editPage.setCityText("Manchester");
        editPage.clickUpdateOwnerSubmit();
        ownersPage.clickHomeLink();
    }

    /**
     * Description: This test changes the telephone number of an owner.
     */
    @Test
    public void testEditOwnerTelephone() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickColMd();
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickPeterMcTavishLink();
        ownersPage.clickEditOwnerLink();

        EditPage editPage = new EditPage(driver);
        editPage.setTelephoneText("1234567890");
        editPage.clickUpdateOwnerSubmit();
        ownersPage.clickHomeLink();
    }

    /**
     * Description: This test changes the name of a pet
     */
    @Test
    public void testEditPetName() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOWNERSLink();

        FindPage findPage = new FindPage(driver);
        findPage.clickColSmOffset();
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickEduardoRodriquezLink();
        ownersPage.clickLink();

        EditPage editPage = new EditPage(driver);
        editPage.setNameText("Jennifer");
        editPage.clickXdContainer();
        editPage.clickUpdatePetSubmit();
        ownersPage.clickHome();
    }


    /**
     * Description: This test changes the DOB of a pet.
     */
    @Test
    public void testEditPetDOB() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.setLastNameText("Black");
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickEditPetLink();

        EditPage editPage = new EditPage(driver);
        editPage.clickBirdCatDogHamsterLizardSnake();
        editPage.clickColSmOffset();
        editPage.clickUpdatePetSubmit();
        ownersPage.clickHome();
    }

    /**
     * Description: This test changes the pet type.
     */
    @Test
    public void testEditPetType() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickFindOwners();

        FindPage findPage = new FindPage(driver);
        findPage.setLastNameText("Black");
        findPage.clickFindOwnerSubmit();

        OwnersPage ownersPage = new OwnersPage(driver);
        ownersPage.clickEditPetLink();

        EditPage editPage = new EditPage(driver);
        editPage.selectTypeSelectOne("dog");
        editPage.clickXdContainer();
        editPage.clickUpdatePetSubmit();
        ownersPage.clickHomeLink();
    }

    /**
     * This test navigates through the veterinarians page, exploring the veterinarians of the pet clinic.
     */
    @Test
    public void testExploreVeterinarians() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        PetClinicaSpringFrameworkdemonstrationPage petClinicaSpringFrameworkdemonstrationPage = new PetClinicaSpringFrameworkdemonstrationPage(driver);
        petClinicaSpringFrameworkdemonstrationPage.clickWelcome();
        petClinicaSpringFrameworkdemonstrationPage.clickVeterinarians();

        VetsPage vetsPage = new VetsPage(driver);
        vetsPage.clickHenryStevens();
        vetsPage.clickHomeLink();
    }

    // Method created to make tests more readable and more maintainable
    public void createOwner(String firstName, String lastName, String address, String city, String telephone,
                            OwnersPage ownersPage) {
        ownersPage.setFirstNameText(firstName);
        ownersPage.setLastNameText(lastName);
        ownersPage.setAddressText(address);
        ownersPage.setCityText(city);
        ownersPage.setTelephoneText(telephone);
        ownersPage.clickAddOwnerSubmit();
    }

}