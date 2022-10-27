import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPageModel extends PageModel {
    private final By fieldName = By.xpath("(.//input[@name='name'])[1]");
    private final By fieldEmail = By.xpath("(.//input[@name='name'])[2]");
    private final By fieldPassword = By.xpath(".//input[@name='Пароль']");
    private final By orderButton = By.xpath("(.//button[text()=\"Зарегистрироваться\"])");
    private final By passwordErrorMessage = By.xpath(".//p[text()=\"Некорректный пароль\"]");
    private final By loginLink = By.xpath(".//a[text()=\"Войти\"]");

    public RegisterPageModel(WebDriver driver){
        super(driver);
    }

    public void register(String name, String email, String password){
        driver.findElement(fieldName).sendKeys(name);
        driver.findElement(fieldEmail).sendKeys(email);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(orderButton).click();
    }

    public void validatePasswordError(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage));
    }

    public void clickLoginLink(){
        driver.findElement(loginLink).click();
    }

    protected String getUrl() {
        return "https://stellarburgers.nomoreparties.site/register";
    }
}