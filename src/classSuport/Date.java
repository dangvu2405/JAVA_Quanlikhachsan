package classSuport;

public class Date {
    private String[] days = new String[31];   // Chỉ nên sử dụng String để lưu trữ ngày
    private String[] months = new String[12]; // Chỉ nên sử dụng String để lưu trữ tháng
    private String[] years = new String[100];  // Chỉ nên sử dụng String để lưu trữ năm

    // Khởi tạo ngày
    public String[] basicDay() {
        for (int i = 0; i < 31; i++) {
            days[i] = String.valueOf(i + 1);
        }
        return days;
    }

    // Khởi tạo tháng
    public String[] basicMonth() {
        for (int i = 0; i < 12; i++) {
            months[i] = String.valueOf(i + 1);
        }
        return months;
    }

    // Khởi tạo năm
    public String[] basicYear() {
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(2023 - i); // Giả sử năm hiện tại là 2023
        }
        return years;
    }
}
