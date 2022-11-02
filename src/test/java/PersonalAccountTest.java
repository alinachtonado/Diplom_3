import io.github.bonigarcia.wdm.WebDriverManager;
import models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PersonalAccountTest {
    private WebDriver driver;
    private User user;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        user = UserOperations.createUser();

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.navigate();

        loginPageModel.login(user.getEmail(), user.getPassword());

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
        UserOperations.deleteUser(user);
        driver.quit();
    }
}