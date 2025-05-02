package PageObjects;
import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends GeneralPage{
    //Locators

//    tìm kiếm tất cả các phần tử <h1> trong tài liệu có văn bản chứa "login".
    private final By loginPageHeader=By.xpath("//h1[contains(text(),'Login')]");
    private final By MyTicketPageHeader=By.xpath("//h1[contains(text(),'Manage ticket')]");
    private final By ChangePassPageHeader=By.xpath("//h1[contains(text(),'Change password')]");

    //Elements

    //Methods
    public void clickTabBookTicket(){
        this.getTabBookTicket().click();
    }
    public void clickTabMyTicket(){
        this.getTabMyTicket().click();
    }
    public void clickTabChangePass(){
        this.getTabChangePass().click();
    }
    public boolean isLoginPageDisplayed() {
        return Constant.WEBDRIVER.findElement(loginPageHeader).isDisplayed();
    }
    public boolean isTabMyTicketDisplayed(){
        return getTabMyTicket().isDisplayed();
    }
    public boolean isTabChangePassDisplayed(){
        return getTabChangePass().isDisplayed();
    }
    public boolean isMyTicketDisplayed(){
        return Constant.WEBDRIVER.findElement(MyTicketPageHeader).isDisplayed();
    }
    public boolean isChangePassDisplayed(){
        return Constant.WEBDRIVER.findElement(ChangePassPageHeader).isDisplayed();
    }
    public HomePage open() {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }
}
