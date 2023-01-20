package eis.company.households.repoeisystems;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.CountWater;

@Repository
public interface CountWaterRepository extends JpaRepository<CountWater, Integer> {
	List<CountWater> findFirst1000ByOrderByTimeStampDesc();
	List<CountWater> findByTimeStampBetween(LocalDateTime dateFrom, LocalDateTime dateTo, Sort sort);
	List<CountWater> findByTimeStampBetweenAndFactoryNumberUspd(LocalDateTime dateFrom, LocalDateTime dateTo, String numUspd, Sort sort);
}
