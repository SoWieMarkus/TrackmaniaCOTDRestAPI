package markus.wieland.tmrestapi.trackmaniarestapi;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.CotdManager;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.COTDDTO;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.OverView;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto.summary.PlayerSummary;
import markus.wieland.tmrestapi.trackmaniarestapi.cotd.models.COTD;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.LeaderBoardManager;
import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.models.LeaderBoard;
import org.springframework.web.bind.annotation.*;

@RestController
public class TrackmaniaRestController {

    private final CotdManager cotdManager;
    private final LeaderBoardManager leaderBoardManager;

    public TrackmaniaRestController(CotdManager cotdManager, LeaderBoardManager leaderBoardManager) {
        this.cotdManager = cotdManager;
        this.leaderBoardManager = leaderBoardManager;
    }

    @PostMapping("/cotd/update/{year}/{month}/{day}/{auth}")
    public void updateDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, @RequestBody COTDDTO cotdDTO, @PathVariable String auth) {
        if (!auth.equals("V2VyIGRpZXNlbiBDb2RlIGtuYWNrdCBpc3QgZ2F5Lg==")) return;
        System.out.println("Valid request");
        cotdManager.update(cotdDTO);
        leaderBoardManager.update(year, month);
    }

    @GetMapping("/cotd/{year}/{month}/{day}")
    public COTD getCotdByYearAndMonthAndDay(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        return cotdManager.getCotdByYearAndMonthAndDay(year, month, day);
    }

    @GetMapping("/cotd/{year}/{month}")
    public LeaderBoard getLeaderBoardByYearAndMonth(@PathVariable int year, @PathVariable int month) {
        return leaderBoardManager.getLeaderBoardByYearAndMonth(year, month);
    }

    @GetMapping("/cotd/global")
    public LeaderBoard getGlobalLeaderBoard() {
        return leaderBoardManager.getGlobalLeaderBoard();
    }

    @GetMapping("/cotd/overview")
    public OverView getAvailableMonths() {
        return cotdManager.getAvailableMonths();
    }

    @GetMapping("/cotd/global/{accountId}")
    public PlayerSummary getGetGlobalPlayerResults(@PathVariable String accountId) {
        return cotdManager.getGlobalPlayerSummary(cotdManager.getAvailableMonths(), accountId);
    }

    @GetMapping("/cotd/summary/{year}/{month}/{accountId}")
    public PlayerSummary getGetGlobalPlayerResults(@PathVariable String accountId, @PathVariable int year, @PathVariable int month) {
        return cotdManager.getSummaryOfMonth(year, month, accountId);
    }


}
