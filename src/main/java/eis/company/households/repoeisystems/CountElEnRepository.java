package eis.company.households.repoeisystems;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.CountElEn;

@Repository
public interface CountElEnRepository extends JpaRepository<CountElEn, Integer> {

	//Optional<CountElEn> findById(Integer id);
	List<CountElEn> findFirst1000ByOrderByTimeStampDesc();
	List<CountElEn> findFirst1000ByTimeStampBetween(LocalDateTime dateFrom, LocalDateTime dateTo, Sort sort);
	List<CountElEn> findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(LocalDateTime dateFrom, LocalDateTime dateTo, String numUspd, Sort sort);
}
