package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ChangePasswordPage extends GeneralPage{
    private final By _txtcurrentPass=By.xpath("//input[@id='currentPassword']");
    private final By _txtnewPassword=By.xpath("//input[@id='newPassword']");
    private final By _txtConfirmPass=By.xpath("//input[@id='confirmPassword']");
    private final By _btnChangePass=By.xpath("//input[@value='Change Password']");
    private final By lblChPassSuccessMessage=By.xpath("//p[contains(text(), 'Your password')]");
    protected WebElement getTxtCurPass(){
        return Constant.WEBDRIVER.findElement(_txtcurrentPass);
    }
    protected WebElement getTxtNewPassword(){
        return Constant.WEBDRIVER.findElement(_txtnewPassword);
    }
    protected WebElement getTxtConfirmPass(){
        return Constant.WEBDRIVER.findElement(_txtConfirmPass);
    }
    protected WebElement getBtnChangePass(){
        return Constant.WEBDRIVER.findElement(_btnChangePass);
    }
    protected WebElement getlblChPassSuccessMsg(){
        return Constant.WEBDRIVER.findElement(lblChPassSuccessMessage);}
    public HomePage ChangePass(String curpass, String newpassword, String confirmpass){
        //Submit login credentials
        this.getTxtCurPass().sendKeys(curpass);
        this.getTxtNewPassword().sendKeys(newpassword);
        this.getTxtConfirmPass().sendKeys(confirmpass);
        this.getBtnChangePass().click();
        //Land on Home Page
        return new HomePage();
    }
    public String getChPassSuccessMess(){
        return getlblChPassSuccessMsg().getText();
    }
}
