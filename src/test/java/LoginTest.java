import io.github.bonigarcia.wdm.WebDriverManager;
import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private WebDriver driver;
    private User user;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        user = UserOperations.createUser();
    }

    @Test
    public void successfullLoginTest(){
        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.navigate();

        loginPageModel.login(user.getEmail(), user.getPassword());

        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.validateIsOnPage();
        orderPageModel.validateOrderButtonVisible();
    }

    @Test
    public void loginWithIncorrectPasswordTest(){
        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.navigate();

        loginPageModel.login(user.getEmail(), "incorrectpassword");
        loginPageModel.validateIsOnPage();
    }

    @Test
    public void loginThroughMainPageTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();
        orderPageModel.clickEnterAccountButton();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(user.getEmail(), user.getPassword());

        orderPageModel.validateIsOnPage();
        orderPageModel.validateOrderButtonVisible();
    }

    @Test
    public void loginThroughPersonalAreaLinkTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();
        orderPageModel.clickPersonalAreaLink();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(user.getEmail(), user.getPassword());

        orderPageModel.validateIsOnPage();
    }

    @Test
    public void loginThroughRegistrationLinkTest(){
        RegisterPageModel registerPageModel = new RegisterPageModel(driver);
        registerPageModel.navigate();
        registerPageModel.clickLoginLink();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(user.getEmail(), user.getPassword());

        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.validateIsOnPage();
    }

    @Test
    public void loginForgotPasswordPageLinkTest(){
        ForgotPasswordPageModel forgotPasswordPageModel = new ForgotPasswordPageModel(driver);
        forgotPasswordPageModel.navigate();
        forgotPasswordPageModel.clickLoginLink();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(user.getEmail(), user.getPassword());

        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.validateIsOnPage();
    }

    @After
    public void cleanUp() {
        UserOperations.deleteUser(user);
        driver.quit();
    }
}