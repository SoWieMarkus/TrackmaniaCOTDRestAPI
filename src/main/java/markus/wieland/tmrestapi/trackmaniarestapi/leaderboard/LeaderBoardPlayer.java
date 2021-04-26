package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeaderBoardPlayer {
    @Id
    private String id;

    private int amountFirst;
    private int amountSecond;
    private int amountThird;

    private String displayName;
    private String accountId;

    @Column(columnDefinition="TEXT")
    private String zone;

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    private int points;

    public String getId() {
        return id;
    }

    public static String buildId(String id) {
        return "global_"+id;
    }

    public static String buildId(int year, int month, String id) {
        return year + "_" + month + "_" + id;
    }

    public void update(LeaderBoardTempPlayer leaderBoardTempPlayer) {
        this.displayName = leaderBoardTempPlayer.getPlayerName();
        this.accountId = leaderBoardTempPlayer.getPlayerId();
        this.amountFirst = leaderBoardTempPlayer.getAmountFirst();
        this.amountSecond = leaderBoardTempPlayer.getAmountSecond();
        this.amountThird = leaderBoardTempPlayer.getAmountThird();
        this.points = leaderBoardTempPlayer.getPoints();
        this.zone = leaderBoardTempPlayer.getZone();
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmountFirst() {
        return amountFirst;
    }

    public void setAmountFirst(int amountFirst) {
        this.amountFirst = amountFirst;
    }

    public int getAmountSecond() {
        return amountSecond;
    }

    public void setAmountSecond(int amountSecond) {
        this.amountSecond = amountSecond;
    }

    public int getAmountThird() {
        return amountThird;
    }

    public void setAmountThird(int amountThird) {
        this.amountThird = amountThird;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
