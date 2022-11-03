import io.github.bonigarcia.wdm.WebDriverManager;
import models.OrderPageModel;
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
    public void bunsTabTest() throws InterruptedException {
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickOnIngridientsHeader();

        Thread.sleep(1000);

        orderPageModel.clickOnBunsHeader();

        orderPageModel.validateBunsSelected();
    }

    @Test
    public void sauseTabTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickOnSauceHeader();

        orderPageModel.validateSauceSelected();
    }

    @Test
    public void ingridientsTabTest(){
        OrderPageModel orderPageModel = new OrderPageModel(driver);
        orderPageModel.navigate();

        orderPageModel.clickOnIngridientsHeader();

        orderPageModel.validateIngridientsSelected();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}