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

public class PersonalAccountTest {
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

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();

        loginPageModel.login(email, password);

        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.validateIsOnPage();
    }

    @Test
    public void personalAreaLinkTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickPersonalAreaLink();

        PersonalAccountPageModel personalAccountPageModel = new PersonalAccountPageModel(driver);
        personalAccountPageModel.validateIsOnPage();
    }

    @Test
    public void constructorLinkTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.clickPersonalAreaLink();
        PersonalAccountPageModel personalAccountPageModel = new PersonalAccountPageModel(driver);
        personalAccountPageModel.validateIsOnPage();
        personalAccountPageModel.clickOrderLink();
        orderPageModel.validateIsOnPage();
    }

    @Test
    public void logoLinkTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.clickPersonalAreaLink();
        PersonalAccountPageModel personalAccountPageModel = new PersonalAccountPageModel(driver);
        personalAccountPageModel.validateIsOnPage();
        personalAccountPageModel.clickOnLogo();
        orderPageModel.validateIsOnPage();
    }

    @Test
    public void logOutTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.clickPersonalAreaLink();
        PersonalAccountPageModel personalAccountPageModel = new PersonalAccountPageModel(driver);
        personalAccountPageModel.validateIsOnPage();
        personalAccountPageModel.clickOnLogOutButton();
        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();
    }


    @After
    public void cleanUp() {
        driver.quit();
    }
}