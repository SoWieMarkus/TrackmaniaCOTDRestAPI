package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.PlayerResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaderBoardCreator {

    private final HashMap<String, LeaderBoardTempPlayer> leaderBoard;

    public LeaderBoardCreator() {
        this.leaderBoard = new HashMap<>();
    }

    public void calculate(List<COTD> cotds) {
        for (COTD cotd : cotds) {
            for (PlayerResult playerResult : cotd.getPlayerResult()) {
                LeaderBoardTempPlayer tempPlayer = leaderBoard.get(playerResult.getAccountId());
                if (tempPlayer == null){
                    tempPlayer = new LeaderBoardTempPlayer(playerResult.getDisplayName(), playerResult.getAccountId(), playerResult.getZone());
                    leaderBoard.put(tempPlayer.getPlayerId(), tempPlayer);
                }
                if (playerResult.getPosition() == 0) continue;
                tempPlayer.addResult(playerResult.getPosition());
            }
        }
    }

    public List<LeaderBoardTempPlayer> standings() {
        List<LeaderBoardTempPlayer> standings = new ArrayList<>(leaderBoard.values());
        standings.sort((o1, o2) -> Integer.compare(o2.getPoints(), o1.getPoints()));
        return standings;
    }

}
