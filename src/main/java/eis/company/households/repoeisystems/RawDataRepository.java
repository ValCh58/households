package eis.company.households.repoeisystems;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.RawData;

@Repository
public interface RawDataRepository extends JpaRepository<RawData, Integer> {
	
	List<RawData> findFirst1000ByOrderByEventTimeDesc();
	List<RawData> findFirst1000ByEventTimeBetween(LocalDateTime from, LocalDateTime to, Sort sort);
	List<RawData> findFirst1000ByEventTimeBetweenAndNumUspd(LocalDateTime dateFrom, LocalDateTime dateTo, String numUspd, Sort sort);
}
