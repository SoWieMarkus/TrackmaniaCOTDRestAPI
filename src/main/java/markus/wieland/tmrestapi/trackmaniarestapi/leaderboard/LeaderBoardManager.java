package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.COTDDTO;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.repositories.COTDDayRepository;
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
        Optional<LeaderBoard> global = leaderBoardRepository.findById("global");
        return global.orElse(null);
    }

    public void update(int year, int month) {
        LeaderBoardCreator leaderBoardCreatorGlobal = new LeaderBoardCreator();
        LeaderBoardCreator leaderBoardCreatorMonth = new LeaderBoardCreator();

        leaderBoardCreatorGlobal.calculate(new ArrayList<>((Collection<? extends COTD>) cotdDayRepository.findAll()));
        leaderBoardCreatorMonth.calculate(new ArrayList<>(cotdDayRepository.findByYearAndMonth(year, month)));

        LeaderBoard leaderBoard = leaderBoardRepository.findByYearAndMonth(year, month).orElse(new LeaderBoard(year, month));
        LeaderBoard global = leaderBoardRepository.findById("global").orElse(new LeaderBoard("global"));

        List<LeaderBoardPlayer> leaderBoardPlayers = new ArrayList<>();

        for (LeaderBoardTempPlayer leaderBoardTempPlayer : leaderBoardCreatorMonth.standings()) {
            String id = LeaderBoardPlayer.buildId(year, month, leaderBoardTempPlayer.getPlayerId());
            LeaderBoardPlayer leaderBoardPlayer = leaderBoardPlayerRepository.findById(id).orElse(new LeaderBoardPlayer());
            leaderBoardPlayer.setId(id);
            leaderBoardPlayer.update(leaderBoardTempPlayer);
            leaderBoardPlayers.add(leaderBoardPlayer);
        }

        leaderBoardPlayerRepository.saveAll(leaderBoardPlayers);

        leaderBoard.setLeaderBoardPlayers(leaderBoardPlayers);
        leaderBoardRepository.save(leaderBoard);

        List<LeaderBoardPlayer> leaderBoardPlayersGlobal = new ArrayList<>();

        for (LeaderBoardTempPlayer leaderBoardTempPlayer : leaderBoardCreatorGlobal.standings()) {
            String id = LeaderBoardPlayer.buildId(leaderBoardTempPlayer.getPlayerId());
            LeaderBoardPlayer leaderBoardPlayer = leaderBoardPlayerRepository.findById(id).orElse(new LeaderBoardPlayer());
            leaderBoardPlayer.setId(id);
            leaderBoardPlayer.update(leaderBoardTempPlayer);
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
