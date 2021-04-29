package markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.summary;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.models.PlayerResult;

public class PlayerResultForSummary {

    private int position;
    private int year;
    private int month;
    private int day;

    public PlayerResultForSummary(PlayerResult playerResult, int year, int month, int day) {
        this.position = playerResult.getPosition();
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
