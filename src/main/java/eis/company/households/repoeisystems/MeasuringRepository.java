package eis.company.households.repoeisystems;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.modeleis.Measuring;

@Repository
public interface MeasuringRepository extends JpaRepository<Measuring, Integer> {
	
	//Optional<Measuring> findById(Integer id);
	List<Measuring> findFirst1000ByOrderByTimeStamp(Sort sort);
	List<Measuring> findFirst1000ByOrderByTimeStampAsc();
	List<Measuring> findTop1000ByOrderByTimeStampDesc();
	List<Measuring> findFirst1000ByOrderByTimeStampDesc();
	//Slice<Measuring> slice = findTop300(Pageable pageable);

}
