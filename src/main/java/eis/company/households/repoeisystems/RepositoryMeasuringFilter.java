package eis.company.households.repoeisystems;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.Measuring;

@Repository
public interface RepositoryMeasuringFilter extends JpaRepository<Measuring, Integer> {
	
    List<Measuring> findFirst1000ByTimeStampBetween(LocalDateTime dateFrom, LocalDateTime dateTo, Sort sort);
    List<Measuring> findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(LocalDateTime dateFrom, LocalDateTime dateTo, String numUspd, Sort sort);
}
