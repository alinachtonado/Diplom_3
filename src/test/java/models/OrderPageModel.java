package models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageModel extends PageModel {

    private final By personalAreaLink = By.xpath(".//a[p[text()=\"Личный Кабинет\"]]");
    private final By enterAccountButton = By.xpath(".//button[text()=\"Войти в аккаунт\"]");

    private final By bunsTabHeader = By.xpath(".//div[span[text()=\"Булки\"]]");
    private final By sauceTabHeader = By.xpath(".//div[span[text()=\"Соусы\"]]");
    private final By ingridientsTabHeader = By.xpath(".//div[span[text()=\"Начинки\"]]");

    private final By bunsTabSelectedHeader = By.xpath(".//div[span[text()=\"Булки\"]][contains(@class, 'tab_tab_type_current')]");
    private final By sauceTabSelectedHeader = By.xpath(".//div[span[text()=\"Соусы\"]][contains(@class, 'tab_tab_type_current')]");
    private final By ingridientsTabSelectedHeader = By.xpath(".//div[span[text()=\"Начинки\"]][contains(@class, 'tab_tab_type_current')]");

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

    public void validateBunsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTabSelectedHeader));
    }

    public void validateSauceSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceTabSelectedHeader));
    }

    public void validateIngridientsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(ingridientsTabSelectedHeader));
    }

    public void validateOrderButtonVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    protected String getUrl() {
        return UrlsConstants.orderUrl;
    }
}