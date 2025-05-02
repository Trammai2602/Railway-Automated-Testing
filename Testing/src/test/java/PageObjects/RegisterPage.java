package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RegisterPage extends GeneralPage{
    private final By _txtEmail=By.xpath("//input[@id='email']");
    private final By _txtPassword=By.xpath("//input[@id='password']");
    private final By _txtConfirmPass=By.xpath("//input[@id='confirmPassword']");
    private final By _txtPID=By.xpath("//input[@id='pid']");
    private final By _btnRegister=By.xpath("//input[@value='Register']");
    private final By lblSuccessMessage=By.xpath("//p[contains(text(), 'Registration Confirmed!')]");
    private final By lblErMessage=By.xpath("//p[@class='message error']");
    private final By lblpasswordErrorMessage = By.cssSelector("label[for='password'].validation-error");
    private final By lblpidErrorMessage=By.xpath("//label[@for='pid' and @class='validation-error']");

    protected WebElement getTxtEmail(){
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }
    protected WebElement getTxtPassword(){
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    protected WebElement getTxtConfirmPass(){
        return Constant.WEBDRIVER.findElement(_txtConfirmPass);
    }
    protected WebElement getTxtpid(){
        return Constant.WEBDRIVER.findElement(_txtPID);
    }
    protected WebElement getBtnRegister(){
        return Constant.WEBDRIVER.findElement(_btnRegister);
    }
    public WebElement getLblErMessage() {return Constant.WEBDRIVER.findElement(lblErMessage);}
    public WebElement getLblPassErMessage() {return Constant.WEBDRIVER.findElement(lblpasswordErrorMessage);}
    public WebElement getLblpidErMessage() {return Constant.WEBDRIVER.findElement(lblpidErrorMessage);}
    public HomePage register(String email, String password, String confirmpass, String pid){
        //Submit login credentials
        this.getTxtEmail().sendKeys(email);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPass().sendKeys(confirmpass);
        this.getTxtpid().sendKeys(pid);
//        this.getBtnRegister().click();
        WebElement registerButton = Constant.WEBDRIVER.findElement(_btnRegister);
        Actions actions = new Actions(Constant.WEBDRIVER);
        actions.moveToElement(registerButton).click().perform();
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", registerButton);
        this.getBtnRegister().click();
        //Land on Home Page
        return new HomePage();
    }
    public String getSuccessMess(){
        WebElement getlblsuccessMsg=Constant.WEBDRIVER.findElement(lblSuccessMessage);
        return getlblsuccessMsg.getText();
    }
    public String getErMess(){
        return this.getLblErMessage().getText();
    }
    public String getpassErMess(){return this.getLblPassErMessage().getText();}
    public String getpidErMess(){return this.getLblpidErMessage().getText();}
    public boolean isPassErrMsgDisplayed() {
        return Constant.WEBDRIVER.findElement(lblpasswordErrorMessage).isDisplayed();
    }
    public boolean isPidErrMsgDisplayed() {
        return Constant.WEBDRIVER.findElement(lblpidErrorMessage).isDisplayed();
    }
}
