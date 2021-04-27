package markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto;

public class MonthOverView {

    private final int year;
    private final int month;

    public MonthOverView(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }
}
