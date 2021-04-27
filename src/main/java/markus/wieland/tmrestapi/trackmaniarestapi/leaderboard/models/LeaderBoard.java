package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class LeaderBoard {

    @Id
    private String id;

    private int year;
    private int month;

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

    @OneToMany
    private List<LeaderBoardPlayer> leaderBoardPlayers;

    public LeaderBoard(String id) {
        this.id = id;
    }

    public LeaderBoard(int year, int month) {
        this.id  = buildId(year, month);
        this.month = month;
        this.year = year;
    }

    public LeaderBoard() {
    }

    public static String buildId(int year, int month) {
        return "cotd_leaderboard_" + year + "_" + month;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LeaderBoardPlayer> getLeaderBoardPlayers() {
        return leaderBoardPlayers;
    }

    public void setLeaderBoardPlayers(List<LeaderBoardPlayer> leaderBoardPlayers) {
        this.leaderBoardPlayers = leaderBoardPlayers;
    }
}
