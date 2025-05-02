package Testcases;
import PageObjects.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import Common.Utilities;
import Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class LoginTest{
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");
        System.setProperty("webdriver.chrome.driver",Utilities.getProjectPath()+"\\Excutables\\chromedriver.exe");
        Constant.WEBDRIVER=new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    @Test
    public void TC01(){
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage=new HomePage();
//        mở trang chủ
        homePage.open();
//        chuyển đến trang đăng nhập
        LoginPage loginPage=homePage.gotoLoginPage();
//        đăng nhập bằng tên người dùng và mật khẩu từ hằng số Constant.USERNAME và Constant.PASSWORD
//        sau đó lấy thông báo chào mừng từ trang chủ sau khi đăng nhập thành công.
        String actualMsg=loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
//        giả định rằng thông báo chào mừng sẽ có định dạng "Welcome [Tên người dùng]".
        String expectMsg="Welcome "+Constant.USERNAME;
//        So sánh thông báo chào mừng thực tế với thông báo chào mừng mong đợi.
//        kiểm tra xem hai thông báo có khớp nhau không. Nếu chúng không khớp, một thông báo lỗi sẽ được hiển thị
        Assert.assertEquals(actualMsg,expectMsg,"Welcome message is not displayed as expected");
    }
    @Test
    public void TC02() {
        System.out.println("User can't login with blank \"Username\" textbox");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);
        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }
    @Test
    public void TC03(){
        System.out.println("TC03 - User cannot log into Railway with invalid password ");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,"test123");
        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg,expectedErrorMsg,"Error message is not displayed as expected");
    }
    @Test
    public void TC04(){
        System.out.println("TC04 - Login page displays when un-logged User clicks on \"Book ticket\" tab");
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickTabBookTicket();
//       assertTrue:Nếu điều kiện trả về true không có lỗi nào được thông báo
        Assert.assertTrue(homePage.isLoginPageDisplayed(),"Login page is not displayed as expected");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");

        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();

        int maxAttempts = 5;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            // Attempt to login with wrong password
            loginPage.login(Constant.USERNAME, "wrong password");

            // Check error message
            String actualErrorMsg = loginPage.getErrorMessage();
            String expectedErrorMsg;
            if (attempt < maxAttempts) {
//                expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
                expectedErrorMsg = "Invalid username or password. Please try again.";
            } else {
                expectedErrorMsg = "You have used " + (attempt - 1) + " out of " + maxAttempts + " login attempts. After all " + maxAttempts + " have been used, you will be unable to login for 15 minutes.";
            }
            Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
        }
    }
    @Test
    public void TC06(){
        System.out.println("TC06 - Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME,Constant.PASSWORD);
        Assert.assertTrue(homePage.isTabMyTicketDisplayed(),"Tab My Ticket is not displayed as expected");
        Assert.assertTrue(homePage.isTabChangePassDisplayed(),"Tab Change Password is not displayed as expected");
        Assert.assertTrue(homePage.isTabLogoutDisplayed(),"Tab Logout is not displayed as expected");
        homePage.clickTabMyTicket();
        Assert.assertTrue(homePage.isMyTicketDisplayed(),"My Ticket page is not displayed as expected");
        homePage.clickTabChangePass();
        Assert.assertTrue(homePage.isChangePassDisplayed(),"Change Pass page is not displayed as expected");
    }
    @Test
    public void TC07(){
        System.out.println("TC07 - User can create new account");
        HomePage homePage=new HomePage();
        GeneralPage generalPage=new GeneralPage();
        homePage.open();
        RegisterPage registerPage= homePage.gotoRegisterPage();
        registerPage.register("mtram123@gmail.com","trammai1234","trammai12344","123456789");
        String actualMsg=generalPage.getSuccessMess();
//        String expectMsg="Registration Confirmed! You can now log in to the site.";
        String expectMsg="Thank you for registering your account";
        Assert.assertEquals(actualMsg,expectMsg,"Welcome message is not displayed as expected");
    }
    @Test
    public void TC08(){
        System.out.println("TC08 - User can't login with an account hasn't been activated");
        HomePage homePage=new HomePage();
        GeneralPage generalPage=new GeneralPage();
        homePage.open();
        LoginPage loginPage= homePage.gotoLoginPage();
        loginPage.login("Mtram@gmail.com","123tram123");
        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "Invalid username or password. Please try again.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");

    }
    @Test
    public void TC09(){
        System.out.println("TC09 - User can change password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        ChangePasswordPage changePasswordPage=homePage.gotoChangePasspage();
        changePasswordPage.ChangePass("mk12345678","mkhau123456","mkhau123456");
        String actualMsg =changePasswordPage.getChPassSuccessMess();
//        String expectMsg="Your password has been updated!";
        String expectMsg="Your password has been updated";
        Assert.assertEquals(actualMsg,expectMsg,"Success message is not displayed as expected");
    }
    @Test
    public void TC10(){
        System.out.println("TC10 - User can't create account with \"Confirm password\" is not the same with \"Password\"");
        HomePage homePage=new HomePage();
        homePage.open();
        RegisterPage registerPage= homePage.gotoRegisterPage();
        registerPage.register("maitram123@gmail.com","trammai","trammai123","123456789");
        String actualMsg=registerPage.getErMess();
        String expectMsg="There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg,expectMsg,"Error message is not displayed as expected");
    }
    @Test
    public void TC11(){
        System.out.println("TC11 - User can't create account while password and PID fields are empty");
        HomePage homePage=new HomePage();
        homePage.open();
        RegisterPage registerPage= homePage.gotoRegisterPage();
        registerPage.register("maitram123@gmail.com","","","");

        String actualMsg=registerPage.getErMess();
        String expectMsg="There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg,expectMsg,"Error message is not displayed as expected");

        String actualPasswordMsg = registerPage.getpassErMess();
        String expectedPasswordMsg = "Invalid password length.";
        Assert.assertEquals(actualPasswordMsg, expectedPasswordMsg, "Invalid password length message is not displayed as expected");

        String actualpidMsg = registerPage.getpidErMess();
        String expectedpidMsg = "Invalid ID length.";
        Assert.assertEquals(actualpidMsg, expectedpidMsg, "Invalid ID length message is not displayed as expected");
    }
    @Test
    public void TC12(){
        System.out.println("TC12 - Errors display when password reset token is blank");
        HomePage homePage=new HomePage();
        homePage.open();
        LoginPage loginPage=new LoginPage();
        loginPage.gotoLoginPage();
        loginPage.gotoForgotPasspage();
        loginPage.forgotpass(Constant.USERNAME);

    }
    @Test
    public  void TC14(){
        System.out.println("TC14 - User can book 1 ticket at a time");
        HomePage homePage=new HomePage();
        homePage.open();
        LoginPage loginPage=new LoginPage();
        loginPage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        BookTicketPage bookTicketPage= homePage.gotoBookTicket();
        bookTicketPage.scrollDownToBookTicketButton();

        int plus=11;
        String formattedDepartDate = Utilities.selectDepartDate(plus);
//        String nextDepartDateString = Utilities.calculateNextDepartDate(plus);
        // Select booking details
        bookTicketPage.BookTicket( "Sài Gòn", "Nha Trang", "Soft bed with air conditioner",formattedDepartDate, "1");

        // Verify success message and ticket information
        Assert.assertEquals(bookTicketPage.getBookTicketSuccessMess(), "Ticket booked successfully!");
        boolean ticketDetailsVerified = bookTicketPage.verifyTicketDetails("Sài Gòn", "Nha Trang", "Soft bed with air conditioner", formattedDepartDate, "1" );

        Assert.assertTrue(ticketDetailsVerified, "Ticket details do not match.");
    }
    @Test
    public void TC15(){
        System.out.println("TC15 - User can open \"Book ticket\" page by clicking on \"Book ticket\" link in \"Train timetable\" page");
        HomePage homePage=new HomePage();
        homePage.open();
        LoginPage loginPage=new LoginPage();
        loginPage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        TimetablePage timetablePage= homePage.gotoTimetable();
//        timetablePage.scrollDownToBookTicketButton();
        timetablePage.clickBookTicketLink("Huế","Sài Gòn");
        BookTicketPage bookTicketPage=new BookTicketPage();
        bookTicketPage.scrollDownToBookTicketButton();
        Assert.assertEquals(bookTicketPage.getSelectDepartFrom(),"Huế");
        Assert.assertEquals(bookTicketPage.getSelectArriveAt(),"Sài Gòn");
    }
    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        MyTicketPage myticketPage = homePage.gotoMyTicket(); // Khởi tạo đối tượng MyticketPage
        myticketPage.scrollDownToBookTicketButton();
        String departStation = "Quảng Nam";
        String arriveStation = "Sài Gòn";
        String seatType = "Soft bed with air conditioner";
        String departDate = "4/17/2024";

        int numberOfTicketsBeforeCancellation = myticketPage.getNumberOfTickets(departStation,arriveStation,seatType,departDate);
        System.out.println(numberOfTicketsBeforeCancellation);
        // Kiểm tra xem có vé tồn tại không trước khi hủy
        if (numberOfTicketsBeforeCancellation == 0) {
            System.out.println("Không có vé nào để hủy.");
            return; // Dừng thực hiện phương thức nếu không có vé tồn tại
        }
// Hủy vé ở đây
        myticketPage.cancelTicket(departStation,arriveStation,seatType,departDate);

        Alert alert = Constant.WEBDRIVER.switchTo().alert();

        alert.accept();
        int numberOfTicketsAfterCancellation = myticketPage.getNumberOfTickets(departStation,arriveStation,seatType,departDate);
        System.out.println(numberOfTicketsAfterCancellation);
        if (numberOfTicketsBeforeCancellation == 1) {
            // Trường hợp chỉ có 1 vé, kiểm tra xem vé có còn hiển thị sau khi hủy hay không
            Assert.assertFalse(myticketPage.isTicketDisplayed(departStation,arriveStation,seatType,departDate));
        } else {
            // Trường hợp có nhiều vé, kiểm tra xem số lượng vé sau khi hủy có giảm đi 1 so với trước khi hủy hay không
            Assert.assertEquals(numberOfTicketsBeforeCancellation - 1, numberOfTicketsAfterCancellation);
        }

    }
}