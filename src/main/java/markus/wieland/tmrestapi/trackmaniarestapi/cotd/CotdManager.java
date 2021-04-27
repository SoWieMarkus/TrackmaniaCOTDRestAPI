package markus.wieland.tmrestapi.trackmaniarestapi.cotd;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.COTDDTO;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.PlayerResultDTO;
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

    public void update(int year, int month, int day, COTDDTO cotddto) {
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


}
