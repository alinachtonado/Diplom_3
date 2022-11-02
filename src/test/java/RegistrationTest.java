import io.github.bonigarcia.wdm.WebDriverManager;
import models.LoginPageModel;
import models.RegisterPageModel;
import models.User;
import models.UserOperations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    private WebDriver driver;
    private RegisterPageModel pageModel;
    private User user;


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
        user = new User(email, "goodpassword", "Somename");
        pageModel.register(user.getName(), user.getEmail(), user.getPassword());

        LoginPageModel loginPageModel = new LoginPageModel(driver);
        loginPageModel.validateIsOnPage();
    }

    @Test
    public void failRegistrationTest(){
        String email = String.format("a%s@gmail.com", java.util.UUID.randomUUID());
        user = new User(email, "bad", "somename");
        pageModel.register(user.getName(), user.getEmail(), user.getPassword());

        pageModel.validatePasswordError();
        user = null;
    }

    @After
    public void cleanUp() {
        if (user != null){
            UserOperations.deleteUser(user);
        }

        driver.quit();
    }
}