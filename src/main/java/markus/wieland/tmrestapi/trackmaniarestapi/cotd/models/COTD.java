package markus.wieland.tmrestapi.trackmaniarestapi.cotd.models;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.COTDDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class COTD {

    @Id
    private long id;

    private String name;

    private int year;
    private int month;
    private int day;

    private long startTime;
    private long endTime;
    private long leaderBoardId;
    private int players;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany
    private List<PlayerResult> playerResult;

    public COTD(COTDDTO cotddto) {
        this.name = cotddto.getName();
        this.endTime = cotddto.getEndTime();
        this.startTime = cotddto.getStartTime();
        this.year = cotddto.getYear();
        this.month = cotddto.getMonth();
        this.day = cotddto.getDay();
        this.id = cotddto.getId();

        this.players = cotddto.getPlayers();
        this.leaderBoardId = cotddto.getLeaderBoardId();
    }

    public COTD() {
    }

    public void update(COTDDTO dto) {
        this.name = dto.getName();
        this.endTime = dto.getEndTime();
        this.startTime = dto.getStartTime();
        this.year = dto.getYear();
        this.month = dto.getMonth();
        this.day = dto.getDay();

        this.players = dto.getPlayers();
        this.leaderBoardId = dto.getLeaderBoardId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getLeaderBoardId() {
        return leaderBoardId;
    }

    public void setLeaderBoardId(long leaderBoardId) {
        this.leaderBoardId = leaderBoardId;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public List<PlayerResult> getPlayerResult() {
        return playerResult;
    }

    public void setPlayerResult(List<PlayerResult> playerResult) {
        this.playerResult = playerResult;
    }
}
