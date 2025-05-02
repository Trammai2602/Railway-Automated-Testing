package Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }
    public static String selectDepartDate(int daysToAdd) {
        System.out.println("Calling selectDepartDate method...");
        // Lấy ngày hiện tại
        LocalDate today = LocalDate.now();

        // Thêm số ngày cần thiết
        LocalDate desiredDate = today.plusDays(daysToAdd);
        if (daysToAdd >= 3 && daysToAdd <= 30) {
            // Chuyển đổi định dạng ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            String formattedDate = desiredDate.format(formatter);
            System.out.println("Selected departure date: " + formattedDate);
            return formattedDate;
        } else {
            System.out.println("We only have tickets for 3-30 days ahead.");
            System.out.println("Please come to the station to buy a ticket if you need to depart within 2 days.");
            return null; // hoặc throw một exception tùy thuộc vào logic của ứng dụng
        }
    }
}
