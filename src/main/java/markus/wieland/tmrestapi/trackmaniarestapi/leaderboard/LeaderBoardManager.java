package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.models.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.repositories.COTDDayRepository;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.calculate.LeaderBoardCreator;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.calculate.LeaderBoardTempPlayer;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.models.LeaderBoard;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.models.LeaderBoardPlayer;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.repositories.LeaderBoardPlayerRepository;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.repositories.LeaderBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class LeaderBoardManager {

    private LeaderBoardPlayerRepository leaderBoardPlayerRepository;
    private LeaderBoardRepository leaderBoardRepository;
    private COTDDayRepository cotdDayRepository;

    private static final String GLOBAL_LEADER_BOARD = "global";

    @Autowired
    public void setLeaderBoardPlayerRepository(LeaderBoardPlayerRepository leaderBoardPlayerRepository) {
        this.leaderBoardPlayerRepository = leaderBoardPlayerRepository;
    }

    @Autowired
    public void setLeaderBoardRepository(LeaderBoardRepository leaderBoardRepository) {
        this.leaderBoardRepository = leaderBoardRepository;
    }

    public LeaderBoard getLeaderBoardByYearAndMonth(int year, int month) {
        return leaderBoardRepository.findByYearAndMonth(year, month).orElse(null);
    }

    public LeaderBoard getGlobalLeaderBoard() {
        Optional<LeaderBoard> global = leaderBoardRepository.findById(GLOBAL_LEADER_BOARD);
        return global.orElse(null);
    }

    public void update(int year, int month) {
        LeaderBoardCreator leaderBoardCreatorGlobal = new LeaderBoardCreator();
        LeaderBoardCreator leaderBoardCreatorMonth = new LeaderBoardCreator();

        leaderBoardCreatorGlobal.calculate(new ArrayList<>((Collection<? extends COTD>) cotdDayRepository.findAll()));
        leaderBoardCreatorMonth.calculate(new ArrayList<>(cotdDayRepository.findByYearAndMonth(year, month)));

        LeaderBoard leaderBoard = leaderBoardRepository.findByYearAndMonth(year, month).orElse(new LeaderBoard(year, month));
        LeaderBoard global = leaderBoardRepository.findById(GLOBAL_LEADER_BOARD).orElse(new LeaderBoard(GLOBAL_LEADER_BOARD));

        List<LeaderBoardPlayer> leaderBoardPlayers = new ArrayList<>();

        int position = 1;
        for (LeaderBoardTempPlayer leaderBoardTempPlayer : leaderBoardCreatorMonth.standings()) {
            String id = LeaderBoardPlayer.buildId(year, month, leaderBoardTempPlayer.getPlayerId());
            LeaderBoardPlayer leaderBoardPlayer = leaderBoardPlayerRepository.findById(id).orElse(new LeaderBoardPlayer());
            leaderBoardPlayer.setId(id);
            leaderBoardPlayer.update(leaderBoardTempPlayer, position);
            position++;
            leaderBoardPlayers.add(leaderBoardPlayer);
        }

        leaderBoardPlayerRepository.saveAll(leaderBoardPlayers);

        leaderBoard.setLeaderBoardPlayers(leaderBoardPlayers);
        leaderBoardRepository.save(leaderBoard);

        List<LeaderBoardPlayer> leaderBoardPlayersGlobal = new ArrayList<>();

        position = 1;
        for (LeaderBoardTempPlayer leaderBoardTempPlayer : leaderBoardCreatorGlobal.standings()) {
            String id = LeaderBoardPlayer.buildId(leaderBoardTempPlayer.getPlayerId());
            LeaderBoardPlayer leaderBoardPlayer = leaderBoardPlayerRepository.findById(id).orElse(new LeaderBoardPlayer());
            leaderBoardPlayer.setId(id);
            leaderBoardPlayer.update(leaderBoardTempPlayer, position);
            position++;
            leaderBoardPlayersGlobal.add(leaderBoardPlayer);
        }

        leaderBoardPlayerRepository.saveAll(leaderBoardPlayersGlobal);

        global.setLeaderBoardPlayers(leaderBoardPlayersGlobal);
        leaderBoardRepository.save(global);

    }

    @Autowired
    public void setCotdDayRepository(COTDDayRepository cotdDayRepository) {
        this.cotdDayRepository = cotdDayRepository;
    }
}
