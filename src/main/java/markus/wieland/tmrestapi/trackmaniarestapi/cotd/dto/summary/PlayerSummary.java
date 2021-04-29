package markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.summary;

import java.util.List;

public class PlayerSummary {

    private int month;
    private int year;
    private List<PlayerResultForSummary> playerResults;

    public PlayerSummary(int month, int year, List<PlayerResultForSummary> playerResults) {
        this.month = month;
        this.year = year;
        this.playerResults = playerResults;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public List<PlayerResultForSummary> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(List<PlayerResultForSummary> playerResults) {
        this.playerResults = playerResults;
    }
}
