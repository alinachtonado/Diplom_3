import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageModel extends PageModel {

    private final By personalAreaLink = By.xpath(".//p[text()=\"Личный Кабинет\"]");
    private final By enterAccountButton = By.xpath(".//button[text()=\"Войти в аккаунт\"]");

    private final By bunsTabHeader = By.xpath(".//span[text()=\"Булки\"]");
    private final By sauceTabHeader = By.xpath(".//span[text()=\"Соусы\"]");
    private final By ingridientsTabHeader = By.xpath(".//span[text()=\"Начинки\"]");

    private final By bunsLabel = By.xpath(".//p[text()=\"Флюоресцентная булка R2-D3\"]");
    private final By sauceLabel = By.xpath(".//p[text()=\"Соус Spicy-X\"]");
    private final By ingridientLabel = By.xpath(".//p[text()=\"Мясо бессмертных моллюсков Protostomia\"]");

    private final By orderButton = By.xpath(".//button[text()=\"Оформить заказ\"]");

    public OrderPageModel(WebDriver driver){
        super(driver);
    }

    public void clickPersonalAreaLink(){
        driver.findElement(personalAreaLink).click();
    }

    public void clickEnterAccountButton(){
        driver.findElement(enterAccountButton).click();
    }

    public void clickOnBunsHeader() {
        driver.findElement(bunsTabHeader).click();
    }

    public void clickOnIngridientsHeader() {
        driver.findElement(ingridientsTabHeader).click();
    }

    public void clickOnSauceHeader() {
        driver.findElement(sauceTabHeader).click();
    }

    public void validateBunsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsLabel));
    }

    public void validateSauceVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceLabel));
    }

    public void validateIngridientsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(ingridientLabel));
    }

    public void validateOrderButtonVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    protected String getUrl() {
        return "https://stellarburgers.nomoreparties.site/";
    }
}