import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPageModel extends PageModel {
    private final By orderLink = By.xpath(".//p[text()=\"Конструктор\"]");
    private final By logo = By.cssSelector("svg");
    private final By logOutButton = By.xpath(".//button[text()=\"Выход\"]");

    public PersonalAccountPageModel(WebDriver driver){
        super(driver);
    }

    public void clickOrderLink(){
        driver.findElement(orderLink).click();
    }

    public void clickOnLogo(){
        driver.findElement(logo).click();
    }

    public void clickOnLogOutButton(){
        driver.findElement(logOutButton).click();
    }

    protected String getUrl() {
        return "https://stellarburgers.nomoreparties.site/account/profile";
    }
}