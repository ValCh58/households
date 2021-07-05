package eis.company.households.repository;

import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

import eis.company.households.dto.LinkObjectDTO;

@NoRepositoryBean
public interface QueryLinkObjectRepository {

	List<LinkObjectDTO> queryLinkObjectReository();
	
}
