package eis.company.households.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.queres.QueryAcntCountsDto;
import eis.company.households.queres.QueryColdWaterFlowDto;

@Service
public class ReportsService {
	
	@Autowired private QueryAcntCountsDto qAcntCountDto;
	@Autowired private QueryColdWaterFlowDto qColdWaterFlowDto; 

	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<AcntCountsDTO> getAcntCounts(Integer id){
	return qAcntCountDto.getAcntCountsDTO(id);
	}
	
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<ColdWaterFlowDTO> getWaterFlowDto(String factoryNumberUspd, //"%", "2022-01%", "2021-12%", "1000" 
			                                      LocalDate dateFrom, 
			                                      String ratio){
		String dateCurr = dateFrom.toString().substring(0, 7) + "%";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%";
				
		List<ColdWaterFlowDTO> listFlowWaterCold = qColdWaterFlowDto.getQueryResult(factoryNumberUspd,
				                                                                    dateCurr, 
				                                                                    datePrev, 
				                                                                    ratio); 
	return listFlowWaterCold;	
	}

}
