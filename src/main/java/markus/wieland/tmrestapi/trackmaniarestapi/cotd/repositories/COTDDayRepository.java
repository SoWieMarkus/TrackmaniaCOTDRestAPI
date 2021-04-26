package markus.wieland.tmrestapi.trackmaniarestapi.cotd.repositories;

import markus.wieland.tmrestapi.trackmaniarestapi.cotd.COTD;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface COTDDayRepository extends CrudRepository<COTD, Long> {
    Optional<COTD> findByYearAndMonthAndDay(int year, int month, int day);
    List<COTD> findByYearAndMonth(int year, int month);
}
