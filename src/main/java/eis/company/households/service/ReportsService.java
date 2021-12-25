package eis.company.households.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.queres.QueryAcntCountsDto;

@Service
public class ReportsService {
	
	@Autowired QueryAcntCountsDto qacntCountDto;
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<AcntCountsDTO> getAcntCounts(Integer id){
		
		return qacntCountDto.getAcntCountsDTO(id);
		
	}

}
