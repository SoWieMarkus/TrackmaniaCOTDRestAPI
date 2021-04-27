package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.repositories;

import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.models.LeaderBoard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LeaderBoardRepository extends CrudRepository<LeaderBoard, String> {
    Optional<LeaderBoard> findByYearAndMonth(int year, int month);
}
