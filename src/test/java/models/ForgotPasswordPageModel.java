package models;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.Urls;

import static org.hamcrest.CoreMatchers.endsWith;

public class ForgotPasswordPageModel extends PageModel {

    private final By loginLink = By.xpath(".//a[text()=\"Войти\"]");

    public ForgotPasswordPageModel(WebDriver driver){
        super(driver);
    }

    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }

    public void validateIsOnPage(){
        String currentUrl = driver.getCurrentUrl();
        Assert.assertThat(currentUrl, endsWith("/forgot-password"));
    }

    protected String getUrl() {
        return UrlsConstants.forgotPasswordUrl;
    }
}