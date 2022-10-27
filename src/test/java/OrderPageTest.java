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

public class OrderPageTest {
    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void bunsTabTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickOnIngridientsHeader();

        new WebDriverWait(driver, Duration.ofSeconds(1));

        orderPageModel.clickOnBunsHeader();

        orderPageModel.validateBunsVisible();
    }

    @Test
    public void sauseTabTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickOnSauceHeader();

        orderPageModel.validateSauceVisible();
    }

    @Test
    public void ingridientsTabTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickOnIngridientsHeader();

        orderPageModel.validateIngridientsVisible();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}