package markus.wieland.tmrestapi.trackmaniarestapi;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.CotdManager;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.COTDDTO;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.LeaderBoard;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.LeaderBoardManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackmaniaRestController {

    private final CotdManager cotdManager;
    private final LeaderBoardManager leaderBoardManager;

    public TrackmaniaRestController(CotdManager cotdManager, LeaderBoardManager leaderBoardManager) {
        this.cotdManager = cotdManager;
        this.leaderBoardManager = leaderBoardManager;
    }

    @PostMapping("/cotd/update/{year}/{month}/{day}")
    public void updateDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, @RequestBody COTDDTO cotdDTO) {
        cotdManager.update(year, month, day, cotdDTO);
        leaderBoardManager.update(year, month);
    }

    @GetMapping("/cotd/{year}/{month}/{day}")
    public COTD getCotdByYearAndMonthAndDay(@PathVariable int year, @PathVariable int month, @PathVariable int day){
        return cotdManager.getCotdByYearAndMonthAndDay(year, month, day);
    }

    @GetMapping("/cotd/{year}/{month}")
    public LeaderBoard getLeaderBoardByYearAndMonth(@PathVariable int year, @PathVariable int month){
        return leaderBoardManager.getLeaderBoardByYearAndMonth(year, month);
    }

    @GetMapping("/cotd/global")
    public LeaderBoard getGlobalLeaderBoard(){
        return leaderBoardManager.getGlobalLeaderBoard();
    }



}
