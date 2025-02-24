package selenium;


import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenium.page.*;


public class JavaBlogAggregatorTest {

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
     * Description: This test registers a new account and logs in.
     */
    @Test
    public void testRegister() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();
        registerUser("Nithin", "njoy01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        JavaBlogAggregatorLoginPage javaBlogAggregatorLoginPage = new JavaBlogAggregatorLoginPage(driver);

        javaBlogAggregatorLoginPage.setJUsernameText("Nithin");
        javaBlogAggregatorLoginPage.setJPasswordPassword("password123");
        javaBlogAggregatorLoginPage.clickSignInSubmit();
    }

    /**
     * Description: This test creates a new blog.
     */
    @Test
    public void testCreateNewBlog() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();
        registerUser("Joseph", "joseph01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginUser("Joseph", "password123");



        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        javaBlogAggregatorMyaccountPage.clickNewBlogSubmit();

        javaBlogAggregatorMyaccountPage.setNameText("Java Tips Weblog");
        javaBlogAggregatorMyaccountPage.setShortNameText("Java Tips");
        javaBlogAggregatorMyaccountPage.setNickText("Tips for Java");
        javaBlogAggregatorMyaccountPage.setHomepageURLText("https://tips4java.wordpress.com/");
        javaBlogAggregatorMyaccountPage.setRssATOMURLText("https://tips4java.wordpress.com/feed/");
        javaBlogAggregatorMyaccountPage.clickClose();
        javaBlogAggregatorMyaccountPage.clickSaveSubmit();


        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickTopJavaBlogsLink();
        javaBlogAggregatorPage.clickMyAccountLink();
    }

    /**
     * Description: This test edits a blog name.
     */
    @Test
    public void testEditBlogName() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        JavaBlogAggregatorRegisterPage javaBlogAggregatorRegisterPage = new JavaBlogAggregatorRegisterPage(driver);



        javaBlogAggregatorRegisterPage.setNameText("Alen");
        javaBlogAggregatorRegisterPage.setEmailText("alen01@qub.ac.uk");
        javaBlogAggregatorRegisterPage.setPassword("password123");
        javaBlogAggregatorRegisterPage.setPasswordAgainPassword("password123");
        javaBlogAggregatorRegisterPage.clickSaveSubmit();

        // Login
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginUser("Alen", "password123");

        javaBlogAggregatorPage.clickMyAccountLink();
        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);

        // Create a new blog
        addNewBlogEntry("Exception Handling in Java (with Real Examples)",
                "Exception Handling in Java",
                "Exception Handling",
                "https://blog.sentry.io/exception-handling-in-java-with-real-examples/",
                "https://blog.sentry.io/feed.xml",
                javaBlogAggregatorMyaccountPage);

        javaBlogAggregatorPage.clickMyAccountLink();

        javaBlogAggregatorMyaccountPage.clickEditLink();

        JavaBlogAggregatorBlogformPage javaBlogAggregatorBlogformPage = new JavaBlogAggregatorBlogformPage(driver);
        javaBlogAggregatorBlogformPage.setNameText("Examples of performing exception handling in Java");
        javaBlogAggregatorBlogformPage.clickSaveSubmit();

        BlogformPage blogformPage = new BlogformPage(driver);
        blogformPage.clickTopJavaBlogsLink();
        javaBlogAggregatorPage.clickMyAccountLink();
    }

    /**
     * Description: This test edits a blogs short name.
     */
    @Test
    public void testEditBlogShortName() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Jerin", "jerin01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginUser("Jerin", "password123");

        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry(
                "Top 10 Java Exception handling best practices",
                "Java Exception handling best practices",
                "Java Exception Handling",
                "https://www.theserverside.com/blog/Coffee-Talk-Java-News-Stories-and-Opinions/Java-Exception-handling-best-practices",
                "https://www.theserverside.com/rss/theserverside-events-rss2-os.xml",
                javaBlogAggregatorMyaccountPage);

        javaBlogAggregatorPage.clickMyAccountLink();
        javaBlogAggregatorMyaccountPage.clickEditLink();

        JavaBlogAggregatorBlogformPage javaBlogAggregatorBlogformPage = new JavaBlogAggregatorBlogformPage(driver);
        javaBlogAggregatorBlogformPage.setShortNameText("Best example of Java exception handling");
        javaBlogAggregatorBlogformPage.clickSaveSubmit();
    }

    /**
     * Description: This test edits the blog's Nick
     */
    @Test
    public void testEditBlogNick() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Fleming", "fleming01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginUser("Fleming", "password123");


        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry("How to build a Web Application Using Java",
                "Use Java to build web application",
                "Web Application using Java",
                "https://www.javatpoint.com/how-to-build-a-web-application-using-java",
                "https://feeds.feedburner.com/javatpointsonoo",
                javaBlogAggregatorMyaccountPage);

        javaBlogAggregatorPage.clickMyAccountLink();

        javaBlogAggregatorMyaccountPage.clickEditLink();

        JavaBlogAggregatorBlogformPage javaBlogAggregatorBlogformPage = new JavaBlogAggregatorBlogformPage(driver);
        javaBlogAggregatorBlogformPage.setNickText("Creating Web Application");
        javaBlogAggregatorBlogformPage.clickSaveSubmit();
    }

    /**
     * Description: This test edits the Homepage URL of the blog.
     */
    @Test
    public void testEditBlogHomepageURL() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Josh", "josh01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginUser("Josh", "password123");

        javaBlogAggregatorPage.clickMyAccountLink();

        // Create a new blog
        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry(
                "venkatjavasource - Java As programming language",
                "Java Fundamentals",
                "Java",
                "https://venkatjavasource.wordpress.com/category/java-design-patterns/",
                "https://venkatjavasource.wordpress.com/feed/",
                javaBlogAggregatorMyaccountPage);

        javaBlogAggregatorPage.clickMyAccountLink();

        javaBlogAggregatorMyaccountPage.clickEditLink();

        JavaBlogAggregatorBlogformPage javaBlogAggregatorBlogformPage = new JavaBlogAggregatorBlogformPage(driver);
        javaBlogAggregatorBlogformPage.clickLabel();
        javaBlogAggregatorBlogformPage.setHomepageURLText
                ("https://venkatjavasource.wordpress.com/category/java-as-programming-language/");
        javaBlogAggregatorBlogformPage.clickSaveSubmit();
    }


    /**
     * Description: This test edits the blog's RSS URL.
     */
    @Test
    public void testEditBlogRSSURL() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();
        registerUser("Jimmy", "jimmy01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginUser("Jimmy", "password123");
        javaBlogAggregatorPage.clickMyAccountLink();


        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry("Short and informative posts about java, OOP and general software engineering goodness.",
                "Java Programming to an Interface",
                "Java Programming",
                "https://javabreakpoint.wordpress.com/",
                "https://javabreakpoint.wordpress.com/feed",
                javaBlogAggregatorMyaccountPage);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickEditLink();

        JavaBlogAggregatorBlogformPage javaBlogAggregatorBlogformPage = new JavaBlogAggregatorBlogformPage(driver);
        javaBlogAggregatorBlogformPage.clickLabel();
        javaBlogAggregatorBlogformPage.setRssATOMURLText("https://javabreakpoint.wordpress.com/feed/");
        javaBlogAggregatorBlogformPage.clickSaveSubmit();
    }


    /**
     * Description: This test deletes a blog which was added.
     */
    @Test
    public void testRemoveBlog() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("James", "james01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();
        loginUser("James", "password123");

        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry("How to build a Spring-boot application",
                "Build Spring-Boot Application",
                "Spring-boot Application",
                "https://spring.io/guides/gs/spring-boot",
                "https://spring.io/guides/gs/spring-boot",
                javaBlogAggregatorMyaccountPage);

        javaBlogAggregatorPage.clickMyAccountLink();

        javaBlogAggregatorMyaccountPage.clickRemoveSubmit();
        javaBlogAggregatorMyaccountPage.clickDeleteSubmit();
        javaBlogAggregatorMyaccountPage.clickTopJavaBlogsLink();
        javaBlogAggregatorPage.clickMyAccountLink();
    }

    /**
     * Description: This test accesses the news tab.
     */
    @Test
    public void testAccessingNews() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickNewsLink();

        JavaBlogAggregatorNewsPage javaBlogAggregatorNewsPage = new JavaBlogAggregatorNewsPage(driver);
        javaBlogAggregatorNewsPage.clickTopJavaBlogsLink();
    }

    /**
     * Description: This test checks if a user can log out successfully.
     */
    @Test
    public void testLogout() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Jins", "jins01@qub.ac.uk", "password123");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginUser("Jins", "password123");

        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        javaBlogAggregatorMyaccountPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickTopJavaBlogsLink();
    }

    /**
     * Description: This test consists of an admin removing a user.
     */
    @Test
    public void testAdminRemoveUser() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("John", "john123@gmail.com", "ppppp");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginUser("admin", "admin");

        javaBlogAggregatorPage.clickAdministerButton();
        javaBlogAggregatorPage.clickUsersLink();

        JavaBlogAggregatorUsersPage javaBlogAggregatorUsersPage = new JavaBlogAggregatorUsersPage(driver);
        javaBlogAggregatorUsersPage.clickSubmit();
        javaBlogAggregatorUsersPage.clickDeleteSubmit();
        javaBlogAggregatorUsersPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setJUsernameText("John");
        loginPage.setJPasswordPassword("ppppp");
        loginPage.clickSignInSubmit();
        loginPage.clickDiv();
    }

    /**
     * Description: This test consists of an admin removing a blog which was added by a user.
     */
    @Test
    public void testAdminRemoveBlog() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Martha", "martha123@gmail.com", "ppppp");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginUser("Martha", "ppppp");

        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry("Spring Fundamentals - A Quick Walkthrough",
                "Spring Fundamentals",
                "Spring",
                "https://supundharmarathne.wordpress.com/2013/07/28/spring-fundamentals-a-quick-walkthrough/",
                "https://supundharmarathne.wordpress.com/feed/",
                javaBlogAggregatorMyaccountPage);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickBlogsLink();

        JavaBlogAggregatorBlogsPage javaBlogAggregatorBlogsPage = new JavaBlogAggregatorBlogsPage(driver);
        javaBlogAggregatorBlogsPage.clickTopJavaBlogsLink();
        javaBlogAggregatorPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setJUsernameText("admin");
        loginPage.setJPasswordPassword("admin");
        loginPage.clickSignInSubmit();
        javaBlogAggregatorPage.clickBlogsLink();


        javaBlogAggregatorBlogsPage.clickSubmit();
        javaBlogAggregatorBlogsPage.clickDeleteSubmit();
        javaBlogAggregatorBlogsPage.clickTopJavaBlogsLink();
        javaBlogAggregatorPage.clickBlogsLink();
        javaBlogAggregatorBlogsPage.clickLogoutLink();
        loginPage.clickDiv();
    }

    /**
     * Description: This test alters the admin name and password.
     */
    @Test
    public void testAlterAdminDetails() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickLoginLink();

        loginUser("admin", "admin");

        javaBlogAggregatorPage.clickAdministerButton();
        javaBlogAggregatorPage.clickAdminDetailLink();

        JavaBlogAggregatorAdmindetailPage javaBlogAggregatorAdmindetailPage = new JavaBlogAggregatorAdmindetailPage(driver);
        javaBlogAggregatorAdmindetailPage.setNameText("admin2");
        javaBlogAggregatorAdmindetailPage.setUsernamePassword("ppppp");
        javaBlogAggregatorAdmindetailPage.clickSaveSubmit();

        DetailPage detailPage = new DetailPage(driver);
        detailPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setJUsernameText("admin");
        loginPage.setJPasswordPassword("admin");
        loginPage.clickSignInSubmit();
        loginPage.setJUsernameText("admin2");
        loginPage.setJPasswordPassword("ppppp");
        loginPage.clickSignInSubmit();

        javaBlogAggregatorPage.clickAdministerButton();
        javaBlogAggregatorPage.clickAdminDetailLink();

        javaBlogAggregatorAdmindetailPage.setNameText("admin");
        javaBlogAggregatorAdmindetailPage.setUsernamePassword("admin");
        javaBlogAggregatorAdmindetailPage.clickSaveSubmit();

        detailPage.clickLogoutLink();

        loginPage.setJUsernameText("admin");
        loginPage.setJPasswordPassword("admin");
        loginPage.clickSignInSubmit();
    }

    /**
     * Description: This test consists of a user adding a blog, and then the admin creating a category and then
     * assigning the blog to the category created.
     */
    @Test
    public void testCreateCategoryAndAssignToBlog() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Richard", "richard123@gmail.com", "ppppp");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginUser("Richard", "ppppp");

        javaBlogAggregatorPage.clickMyAccountLink();

        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        addNewBlogEntry("Spring-Clean Your Blog in Five Easy Steps",
                "Spring-Clean your blog",
                "Spring-Clean",
                "https://wordpress.com/blog/2014/03/18/declutter-your-blog/",
                "https://wordpress.com/blog/2014/03/18/declutter-your-blog/feed/",
                javaBlogAggregatorMyaccountPage);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutLink();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setJUsernameText("admin");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        loginPage.setJPasswordPassword("admin");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        loginPage.clickSignInSubmit();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorPage.clickAdministerButton();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorPage.clickCategoriesLink();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        JavaBlogAggregatorAdmincategoriesPage javaBlogAggregatorAdmincategoriesPage = new JavaBlogAggregatorAdmincategoriesPage(driver);
        javaBlogAggregatorAdmincategoriesPage.clickNewCategorySubmit();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorAdmincategoriesPage.setNameText("Spring-Cleaning");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorAdmincategoriesPage.setShortNameText("Spring-Clean");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorAdmincategoriesPage.clickSaveSubmit();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorAdmincategoriesPage.clickBlogsLink();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());

        JavaBlogAggregatorBlogsPage javaBlogAggregatorBlogsPage = new JavaBlogAggregatorBlogsPage(driver);
        javaBlogAggregatorBlogsPage.selectSelectOne("Spring-Cleaning");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorBlogsPage.clickLogoutLink();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        loginPage.setJUsernameText("Richard");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        loginPage.setJPasswordPassword("ppppp");
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        loginPage.clickSignInSubmit();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorPage.clickBlogsLink();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        javaBlogAggregatorBlogsPage.clickTopJavaBlogsLink();
    }

    /**
     * Description: This test consists of an admin creating a piece of news, logging out and seeing if news is still
     * visible when no one is logged in.
     */
    @Test
    public void testAdminCreateNews() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickLoginLink();

        loginUser("admin", "admin");

        javaBlogAggregatorPage.clickAdministerButton();
        javaBlogAggregatorPage.clickAddNewsLink();

        AddPage addPage = new AddPage(driver);
        addPage.setTitleText("Breaking News: Test");
        addPage.setShortDescriptionText("This is a test");
        addPage.setDescriptionTextarea("<div class='jumbotron'>\nNews description test.\n</div>\n");
        addPage.clickSaveSubmit();
        addPage.clickNewsLink();

        JavaBlogAggregatorNewsPage javaBlogAggregatorNewsPage = new JavaBlogAggregatorNewsPage(driver);
        javaBlogAggregatorNewsPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickNewsLink();
        javaBlogAggregatorNewsPage.clickBreakingNewsTestLink();

        JavaBlogAggregatorBreakingNewsTestPage javaBlogAggregatorBreakingNewsTestPage = new JavaBlogAggregatorBreakingNewsTestPage(driver);
        javaBlogAggregatorBreakingNewsTestPage.clickTopJavaBlogsLink();

    }

    /**
     * Description: This test consists of an admin editing all aspects of a blog.
     */
    @Test
    public void testAdminEditBlog() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickRegisterLink();

        registerUser("Mary", "mary123@gmail.com", "ppppp");

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginUser("Mary", "ppppp");

        javaBlogAggregatorPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);

        addNewBlogEntry("Java Deep: Pure Java, nothing else",
                "Java Deep: Pure Java",
                "Java Deep",
                "https://javax0.wordpress.com/2017/08/16/new-regex-features-in-java-9/",
                "https://javax0.wordpress.com/feed/",
                javaBlogAggregatorMyaccountPage);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setJUsernameText("admin");
        loginPage.setJPasswordPassword("admin");
        loginPage.clickSignInSubmit();
        javaBlogAggregatorPage.clickBlogsLink();

        JavaBlogAggregatorBlogsPage javaBlogAggregatorBlogsPage = new JavaBlogAggregatorBlogsPage(driver);
        javaBlogAggregatorBlogsPage.clickLink();

        JavaBlogAggregatorBlogformPage javaBlogAggregatorBlogformPage = new JavaBlogAggregatorBlogformPage(driver);
        javaBlogAggregatorBlogformPage.clickName();
        javaBlogAggregatorBlogformPage.setNameText("The Java Blog: Thoughts, tips and tricks about the Java programming language");
        javaBlogAggregatorBlogformPage.clickLabel2();
        javaBlogAggregatorBlogformPage.setShortNameText("The Java Blog");
        javaBlogAggregatorBlogformPage.clickNick();
        javaBlogAggregatorBlogformPage.setNickText("Java Blog");
        javaBlogAggregatorBlogformPage.setRssATOMURLText("https://thejavablog.wordpress.com/feed/");
        javaBlogAggregatorBlogformPage.setHomepageURLText("https://thejavablog.wordpress.com/");
        javaBlogAggregatorBlogformPage.clickSaveSubmit();
    }

    /**
     * Description: This test consists of an admin creating news and then editing all parts of it.
     */
    @Test
    public void testAdminEditNews() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickLoginLink();

        loginUser("admin", "admin");

        javaBlogAggregatorPage.clickAdministerButton();
        javaBlogAggregatorPage.clickAddNewsLink();

        AddPage addPage = new AddPage(driver);
        addPage.setTitleText("Test title: Editing News Test");
        addPage.setShortDescriptionText("News created to test edit function");
        addPage.setDescriptionTextarea("<div class='jumbotron'>\nThis is some example text for news.\n</div>\n");
        addPage.clickSaveSubmit();
        addPage.clickNewsLink();

        JavaBlogAggregatorNewsPage javaBlogAggregatorNewsPage = new JavaBlogAggregatorNewsPage(driver);
        javaBlogAggregatorNewsPage.clickTestTitleEditingNewsTestLink();

        JavaBlogAggregatorTesttitleEditingNewsTestPage javaBlogAggregatorTesttitleEditingNewsTestPage = new JavaBlogAggregatorTesttitleEditingNewsTestPage(driver);
        javaBlogAggregatorTesttitleEditingNewsTestPage.clickNewsLink();
        javaBlogAggregatorNewsPage.clickEditLink();

        TesttitleeditingnewstestPage testtitleeditingnewstestPage = new TesttitleeditingnewstestPage(driver);
        testtitleeditingnewstestPage.setTitleText("Test title: Editing News Test Edited");
        testtitleeditingnewstestPage.setShortNameText("test-title-editing-news-test edited");
        testtitleeditingnewstestPage.setShortDescriptionText("News created to test edit function edited");
        testtitleeditingnewstestPage.setDescriptionTextarea("<div class='jumbotron'>\nThis is some example text for news. Text has been edited.\n</div>\n");
        testtitleeditingnewstestPage.clickSaveSubmit();

        TesttitleeditingnewstesteditedPage testtitleeditingnewstesteditedPage = new TesttitleeditingnewstesteditedPage(driver);
        testtitleeditingnewstesteditedPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickNewsLink();
        javaBlogAggregatorNewsPage.clickTestTitleEditingNewsTestEditedLink();

        JavaBlogAggregatorTesttitleEditingNewsTestEditedPage javaBlogAggregatorTesttitleEditingNewsTestEditedPage = new JavaBlogAggregatorTesttitleEditingNewsTestEditedPage(driver);
        javaBlogAggregatorTesttitleEditingNewsTestEditedPage.clickTopJavaBlogsLink();

    }

    /**
     * Description: This test consists of an admin creating news and then deleting it.
     */
    @Test
    public void testAdminDeleteNews() throws Throwable {
        driver.get(System.getProperty("BASE_URL", BASE_URL) + "/");

        JavaBlogAggregatorPage javaBlogAggregatorPage = new JavaBlogAggregatorPage(driver);
        javaBlogAggregatorPage.clickLoginLink();

        JavaBlogAggregatorLoginPage javaBlogAggregatorLoginPage = new JavaBlogAggregatorLoginPage(driver);
        javaBlogAggregatorLoginPage.setJUsernameText("admin");
        javaBlogAggregatorLoginPage.setJPasswordPassword("admin");
        javaBlogAggregatorLoginPage.clickSignInSubmit();
        //loginUser("admin", "admin");

        javaBlogAggregatorPage.clickAdministerButton();
        javaBlogAggregatorPage.clickAddNewsLink();

        AddPage addPage = new AddPage(driver);
        addPage.setTitleText("Test Title for news to be deleted");
        addPage.setShortDescriptionText("test description");
        addPage.setDescriptionTextarea("<div class='jumbotron'>\nSample text here.\n</div>\n");
        addPage.clickSaveSubmit();
        addPage.clickMyAccountLink();

        JavaBlogAggregatorMyaccountPage javaBlogAggregatorMyaccountPage = new JavaBlogAggregatorMyaccountPage(driver);
        javaBlogAggregatorMyaccountPage.clickNewsLink();

        JavaBlogAggregatorNewsPage javaBlogAggregatorNewsPage = new JavaBlogAggregatorNewsPage(driver);
        javaBlogAggregatorNewsPage.clickLogoutLink();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickNewsLink();
        javaBlogAggregatorNewsPage.clickLoginLink();
        javaBlogAggregatorLoginPage.setJUsernameText("admin");
        javaBlogAggregatorLoginPage.setJPasswordPassword("admin");
        javaBlogAggregatorLoginPage.clickSignInSubmit();
        javaBlogAggregatorPage.clickNewsLink();
        javaBlogAggregatorNewsPage.clickSubmit();
        javaBlogAggregatorNewsPage.clickDeleteSubmit();
        javaBlogAggregatorNewsPage.clickLogoutLink();

    }

    public void registerUser(String name, String email, String password) {
        JavaBlogAggregatorRegisterPage registerPage = new JavaBlogAggregatorRegisterPage(driver);

        // Set user details on the registration page
        registerPage.setNameText(name);
        registerPage.setEmailText(email);
        registerPage.setPassword(password);
        registerPage.setPasswordAgainPassword(password);
        registerPage.clickSaveSubmit();
    }

    public void loginUser(String username, String password) {
        JavaBlogAggregatorLoginPage loginPage = new JavaBlogAggregatorLoginPage(driver);

        // Set the username and password on the login page
        loginPage.setJUsernameText(username);
        loginPage.setJPasswordPassword(password);
        loginPage.clickSignInSubmit();
    }

    public void addNewBlogEntry(String blogName, String shortName, String nick, String homepageURL, String rssAtomURL,
                                JavaBlogAggregatorMyaccountPage myAccountPage) {
        myAccountPage.clickNewBlogSubmit();

        // Fill in the details for the new blog entry
        myAccountPage.setNameText(blogName);
        myAccountPage.setShortNameText(shortName);
        myAccountPage.setNickText(nick);
        myAccountPage.setHomepageURLText(homepageURL);
        myAccountPage.setRssATOMURLText(rssAtomURL);

        myAccountPage.clickClose();
        myAccountPage.clickSaveSubmit();
    }
}