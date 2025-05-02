package PageObjects;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TimetablePage extends GeneralPage {

    private final By timetable = By.xpath("//table[@class='MyTable WideTable']");

    public BookTicketPage clickBookTicketLink(String depart, String arrive) {
        // Tìm bảng chứa thông tin về các chuyến tàu
        WebElement table = Constant.WEBDRIVER.findElement(timetable);

        // Tìm tất cả các hàng trong bảng
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

        for (WebElement row : rows) {
            // Lấy giá trị của cột Depart Station và Arrive Station trong từng hàng
            WebElement departStationElement = row.findElement(By.xpath("./td[2]"));
            WebElement arriveStationElement = row.findElement(By.xpath("./td[3]"));

            String departStation = departStationElement.getText();
            String arriveStation = arriveStationElement.getText();

            // Kiểm tra xem Depart Station và Arrive Station có phải là các giá trị bạn quan tâm hay không
            if (departStation.equals(depart) && arriveStation.equals(arrive)) {
                // Nếu phù hợp, lấy liên kết "book ticket" và nhấp vào nó
                WebElement bookTicketLink = row.findElement(By.xpath("./td[7]/a[text()='book ticket']"));
                ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketLink);
                bookTicketLink.click();

                return new BookTicketPage();
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
}

