import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

public class RegistrationTest {
    private WebDriver driver;
    private RegisterPageModel pageModel;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        pageModel = new RegisterPageModel(driver);
        pageModel.navigate();
    }

    @Test
    public void successfullRegistrationTest(){
        String email = String.format("a%s@gmail.com", java.util.UUID.randomUUID());
        pageModel.register("Somename", email, "goodpassword");

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();
    }

    @Test
    public void failRegistrationTest(){
        String email = String.format("a%s@gmail.com", java.util.UUID.randomUUID());
        pageModel.register("Somename", email, "bad");

        pageModel.validatePasswordError();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}