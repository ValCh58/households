package eis.company.households.repoeisystems;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.CountHeat;

@Repository
public interface CountHeaterRepository extends JpaRepository<CountHeat, Integer> {
	
	List<CountHeat> findFirst1000ByOrderByTimeStampDesc();
	List<CountHeat> findByTimeStampBetween(LocalDateTime dateFrom, LocalDateTime dateTo, Sort sort);
	List<CountHeat> findByTimeStampBetweenAndFactoryNumberUspd(LocalDateTime dateFrom, LocalDateTime dateTo, String numUspd, Sort sort);

}
