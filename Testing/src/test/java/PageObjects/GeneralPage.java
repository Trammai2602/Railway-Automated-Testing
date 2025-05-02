package PageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import Constant.Constant;
public class GeneralPage {
//    //: Được sử dụng để tìm phần tử trong bất kỳ vị trí nào trên trang web, không cần quan tâm đến vị trí chính xác của phần tử
    //locators
    private final By tabLogin=By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout=By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblWelcomeMessage=By.xpath("//div[@class='account']/strong");
    private  final By tabMyTicket=By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private  final By tabChangePassword=By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    private final By tabBookTicket=By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabTimetable=By.xpath("//div[@id='menu']//a[@href='TrainTimeListPage.cshtml']");
    private final By tabRegister=By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    private final By lblSuccessMessage=By.xpath("//p[contains(text(), 'Registration Confirmed!')]");
    public String getSuccessMess(){
        WebElement getlblsuccessMsg=Constant.WEBDRIVER.findElement(lblSuccessMessage);
        return getlblsuccessMsg.getText();
    }
    //Elements
    protected WebElement getTabMyTicket() {
        return Constant.WEBDRIVER.findElement(tabMyTicket);
    }
    protected WebElement getTabChangePass() {
        return Constant.WEBDRIVER.findElement(tabChangePassword);
    }
    protected WebElement getTabBookTicket(){
        return Constant.WEBDRIVER.findElement(tabBookTicket);
    }
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getTabRegister() {
        return Constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabTimetable() {
        return Constant.WEBDRIVER.findElement(tabTimetable);
    }
    protected WebElement getLblWelcomeMessage() {

        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }


    //Methods
    public String getWelcomeMessage() {

        return this.getLblWelcomeMessage().getText();
    }

    public boolean isTabLogoutDisplayed(){
        return this.getTabLogout().isDisplayed();
    }

    public LoginPage gotoLoginPage() {
        this.getTabLogin().click();
        return new LoginPage();
    }
    public RegisterPage gotoRegisterPage(){
        this.getTabRegister().click();
        return  new RegisterPage();
    }
    public ChangePasswordPage gotoChangePasspage(){
        this.getTabChangePass().click();
        return new ChangePasswordPage();
    }
    public BookTicketPage gotoBookTicket(){
        this.getTabBookTicket().click();
        return new BookTicketPage();
    }
    public TimetablePage gotoTimetable(){
        this.getTabTimetable().click();
        return new TimetablePage();
    }
    public MyTicketPage gotoMyTicket(){
        this.getTabMyTicket().click();
        return new MyTicketPage();
    }
}
