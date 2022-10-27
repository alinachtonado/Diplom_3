import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageModel extends PageModel {

    private final By fieldEmail = By.xpath(".//input[@name='name']");
    private final By fieldPassword = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath("(.//button[text()=\"Войти\"])");
    private final By passwordErrorMessage = By.xpath(".//p[text()=\"Некорректный пароль\"]");

    public LoginPageModel(WebDriver driver){
        super(driver);
    }

    public void login(String email, String password){
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void validatePasswordError(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage));
    }

    protected String getUrl() {
        return "https://stellarburgers.nomoreparties.site/login";
    }
}