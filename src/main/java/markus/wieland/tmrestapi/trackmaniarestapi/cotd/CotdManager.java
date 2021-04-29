package markus.wieland.tmrestapi.trackmaniarestapi.cotd;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.COTDDTO;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.MonthOverView;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.OverView;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.PlayerResultDTO;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.summary.PlayerResultForSummary;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.summary.PlayerSummary;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.models.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.models.PlayerResult;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.repositories.COTDDayRepository;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.repositories.PlayerResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CotdManager {

    private COTDDayRepository cotdDayRepository;
    private PlayerResultRepository playerResultRepository;

    @Autowired
    public void setCotdDayRepository(COTDDayRepository cotdDayRepository) {
        this.cotdDayRepository = cotdDayRepository;
    }

    @Autowired
    public void setPlayerResultRepository(PlayerResultRepository playerResultRepository) {
        this.playerResultRepository = playerResultRepository;
    }

    public void update(COTDDTO cotddto) {
        COTD cotd = cotdDayRepository.findById(cotddto.getId()).orElse(new COTD(cotddto));
        List<PlayerResult> playerResults = new ArrayList<>();

        int i = 0;
        for (PlayerResultDTO playerResultDTO : cotddto.getPlayerResultDTOS()) {
            String id = PlayerResult.buildId(cotd.getYear(), cotd.getMonth(), cotd.getDay(),i);
            i++;
            PlayerResult playerResult = playerResultRepository.findById(id).orElse(new PlayerResult(id));
            playerResult.update(playerResultDTO);
            playerResults.add(playerResult);
        }

        playerResultRepository.saveAll(playerResults);

        cotd.setPlayerResult(playerResults);
        cotdDayRepository.save(cotd);
    }

    public COTD getCotdByYearAndMonthAndDay(int year, int month, int day) {
        return cotdDayRepository.findByYearAndMonthAndDay(year, month, day).orElse(null);
    }

    public OverView getAvailableMonths(){
        OverView overViews = new OverView();
        for (COTD cotd : cotdDayRepository.findAll()){
            overViews.add(cotd.getYear(), cotd.getMonth());
        }
        return overViews;
    }

    public PlayerSummary getGlobalPlayerSummary(OverView overView, String accountId){
        List<PlayerResultForSummary> results = new ArrayList<>();
        for (MonthOverView monthOverView : overView.getOverView()) {
            PlayerSummary playerSummary = getSummaryOfMonth(monthOverView.getYear(), monthOverView.getMonth(), accountId);
            if (playerSummary == null) continue;
            results.addAll(playerSummary.getPlayerResults());
        }
        return new PlayerSummary(0,0,results);
    }

    public PlayerSummary getSummaryOfMonth(int year, int month, String accountId){
        List<PlayerResultForSummary> results = new ArrayList<>();
        List<COTD> cotds = cotdDayRepository.findByYearAndMonth(year, month);
        for (COTD cotd : cotds) {
            for (PlayerResult playerResult : cotd.getPlayerResult()) {
                if (playerResult.getAccountId().equals(accountId)) {
                    results.add(new PlayerResultForSummary(playerResult, cotd.getYear(), cotd.getMonth(), cotd.getDay()));
                    break;
                }
            }
        }
        return new PlayerSummary(month,year,results);
    }
}
