package markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.repositories;

import markus.wieland.tmrestapi.trackmaniarestapi.leaderboard.models.LeaderBoardPlayer;
import org.springframework.data.repository.CrudRepository;

public interface LeaderBoardPlayerRepository extends CrudRepository<LeaderBoardPlayer, String> {
}
