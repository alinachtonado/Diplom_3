import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class PageModel {
    protected final WebDriver driver;

    public PageModel(WebDriver driver){
        this.driver = driver;
    }

    public void navigate(){
        driver.get(getUrl());
    }

    public void validateIsOnPage(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.urlToBe(getUrl()));
    }

    protected abstract String getUrl();
}