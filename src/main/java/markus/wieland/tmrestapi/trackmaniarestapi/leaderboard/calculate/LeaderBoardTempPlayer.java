package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.calculate;

import java.util.HashMap;

public class LeaderBoardTempPlayer {

    private int amountFirst;
    private int amountSecond;
    private int amountThird;

    private int points;
    private int bestResult;

    private String playerName;
    private String playerId;
    private String zone;

    private int totalRankingScore;
    private int totalParticipation;

    public void setAmountFirst(int amountFirst) {
        this.amountFirst = amountFirst;
    }

    public void setAmountSecond(int amountSecond) {
        this.amountSecond = amountSecond;
    }

    public void setAmountThird(int amountThird) {
        this.amountThird = amountThird;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    private static final HashMap<Integer, Integer> POINT_SYSTEM = initializePoints();

    public int getAmountFirst() {
        return amountFirst;
    }

    public int getAmountSecond() {
        return amountSecond;
    }

    public int getAmountThird() {
        return amountThird;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerId() {
        return playerId;
    }

    private static HashMap<Integer, Integer> initializePoints() {
        HashMap<Integer, Integer> points = new HashMap<>();
        points.put(1, 1000);
        points.put(2, 700);
        points.put(3, 500);
        points.put(4, 400);
        points.put(5, 320);
        points.put(6, 267);
        points.put(7, 229);
        points.put(8, 200);
        points.put(9, 178);
        points.put(10, 160);
        points.put(11, 145);
        points.put(12, 133);
        points.put(13, 123);
        points.put(14, 114);
        points.put(15, 107);
        points.put(16, 100);
        points.put(17, 94);
        points.put(18, 89);
        points.put(19, 84);
        points.put(20, 80);
        points.put(21, 76);
        points.put(22, 73);
        points.put(23, 70);
        points.put(24, 67);
        points.put(25, 64);
        points.put(26, 62);
        points.put(27, 59);
        points.put(28, 57);
        points.put(29, 55);
        points.put(30, 53);
        points.put(31, 52);
        points.put(32, 50);
        points.put(33, 48);
        points.put(34, 47);
        points.put(35, 45);
        points.put(36, 44);
        points.put(37, 42);
        points.put(38, 41);
        points.put(39, 39);
        points.put(40, 38);
        points.put(41, 36);
        points.put(42, 34);
        points.put(43, 33);
        points.put(44, 31);
        points.put(45, 30);
        points.put(46, 28);
        points.put(47, 27);
        points.put(48, 25);
        points.put(49, 24);
        points.put(50, 23);
        points.put(51, 23);
        points.put(52, 22);
        points.put(53, 21);
        points.put(54, 20);
        points.put(55, 20);
        points.put(56, 19);
        points.put(57, 18);
        points.put(58, 17);
        points.put(59, 16);
        points.put(60, 16);
        points.put(61, 15);
        points.put(62, 14);
        points.put(63, 13);
        points.put(64, 13);
        return points;
    }

    public LeaderBoardTempPlayer(String playerName, String playerId, String zone) {
        this.playerName = playerName;
        this.playerId = playerId;
        this.amountFirst = 0;
        this.amountSecond = 0;
        this.amountThird = 0;
        this.points = 0;
        this.zone = zone;
        this.totalParticipation = 0;
        this.totalRankingScore = 0;
        this.bestResult = 1000;
    }

    public void addResult(int position) {
        switch (position) {
            case 1:
                amountFirst++;
                break;
            case 2:
                amountSecond++;
                break;
            case 3:
                amountThird++;
                break;
        }

        points += POINT_SYSTEM.get(position);
        if (position < bestResult) bestResult = position;
        totalParticipation++;
        totalRankingScore += position;
    }

    public int getBestResult() {
        return bestResult;
    }

    public void setBestResult(int bestResult) {
        this.bestResult = bestResult;
    }

    public int getTotalRankingScore() {
        return totalRankingScore;
    }

    public void setTotalRankingScore(int totalRankingScore) {
        this.totalRankingScore = totalRankingScore;
    }

    public int getTotalParticipation() {
        return totalParticipation;
    }

    public void setTotalParticipation(int totalParticipation) {
        this.totalParticipation = totalParticipation;
    }
}
