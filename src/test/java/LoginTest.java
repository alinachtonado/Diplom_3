import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

public class LoginTest {
    private WebDriver driver;
    private String email;
    private final String password = "somepassword";

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        RegisterPageModel registerPageModel = new RegisterPageModel(driver);
        registerPageModel.navigate();

        email = String.format("a%s@gmail.com", java.util.UUID.randomUUID());
        registerPageModel.register("Somename", email, password);

        new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void successfullLoginTest(){
        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.navigate();

        loginPageModel.login(email, password);

        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.validateIsOnPage();
        orderPageModel.validateOrderButtonVisible();
    }

    @Test
    public void loginWithIncorrectPasswordTest(){
        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.navigate();

        loginPageModel.login(email, "incorrectpassword");
        loginPageModel.validateIsOnPage();
    }

    @Test
    public void loginThroughMainPageTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();
        orderPageModel.clickEnterAccountButton();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(email, password);

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

        loginPageModel.login(email, password);

        orderPageModel.validateIsOnPage();
    }

    @Test
    public void loginThroughRegistrationLinkTest(){
        RegisterPageModel registerPageModel = new RegisterPageModel(driver);
        registerPageModel.navigate();
        registerPageModel.clickLoginLink();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(email, password);

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

        loginPageModel.login(email, password);

        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.validateIsOnPage();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}