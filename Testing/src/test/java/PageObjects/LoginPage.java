package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import Constant.Constant;

public class LoginPage extends GeneralPage{
    //Locators
    private final By _txtUsername=By.xpath("//input[@id='username']");
    private final By _txtPassword=By.xpath("//input[@id='password']");
    private final By _txtEmail=By.xpath("//input[@id='email']");
    private final By _btnLogin=By.xpath("//input[@value='login']");
    private final By _btnSendInstruction=By.xpath("//input[@value='Send Instructions']");
    private final By _lblLoginErrorMsg=By.xpath("//p[@class='message error LoginForm']");
    private final By linkforgotpass=By.xpath("//div[@id='content']//a[@href='/Account/ForgotPassword.cshtml']");

    //Elements
    protected WebElement getTxtUsername(){
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    protected WebElement getTxtPassword(){
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    protected WebElement getTxtEmail(){
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    protected WebElement getBtnLogin(){
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }
    protected WebElement getBtnSendIns(){
        return Constant.WEBDRIVER.findElement(_btnSendInstruction);
    }
    protected WebElement getLblLoginErrorMsg(){
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }
    protected WebElement getForgotpassPage() {
        return Constant.WEBDRIVER.findElement(linkforgotpass);
    }

    //Method
    public HomePage login(String username, String password){
        //Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();

        //Land on Home Page
        return new HomePage();
    }
    public void forgotpass(String email){
        this.getTxtEmail().sendKeys(email);
        this.getBtnSendIns().click();

    }
    public String getErrorMessage() {

        return this.getLblLoginErrorMsg().getText();
    }
    public LoginPage gotoForgotPasspage(){
        this.getForgotpassPage().click();
        return  new LoginPage();
    }
}
