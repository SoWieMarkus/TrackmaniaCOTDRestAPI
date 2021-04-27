package markus.wieland.tmrestapi.trackmaniarestapi.cotd.models;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.PlayerResultDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlayerResult {

    @Id
    private String id;
    private String accountId;
    private String displayName;

    @Column(columnDefinition="TEXT")
    private String zone;

    private int position;

    public static String buildId(int year, int month, int day, int rank) {
        return "cotd_player_result_" + year + "_" + month + "_" + day + "_" + rank;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public PlayerResult(String id) {
        this.id = id;
    }

    public PlayerResult() {
    }

    public void update(PlayerResultDTO playerResultDTO){
        this.displayName = playerResultDTO.getAccountName();
        this.accountId = playerResultDTO.getAccountId();
        this.zone = playerResultDTO.getZone();
        this.position = playerResultDTO.getRank();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
