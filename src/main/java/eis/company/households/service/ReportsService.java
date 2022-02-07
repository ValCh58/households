package eis.company.households.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.dto.ElEnFlowDTO;
import eis.company.households.queres.QueryAcntCountsDto;
import eis.company.households.queres.QueryColdWaterFlowDto;
import eis.company.households.queres.QueryElEnFlow;

@Service
public class ReportsService {
	
	private QueryAcntCountsDto qAcntCountDto;
	private QueryColdWaterFlowDto qColdWaterFlowDto; 
	private QueryElEnFlow queryElEnFlow;

	
	
	
	public ReportsService(QueryAcntCountsDto qAcntCountDto, QueryColdWaterFlowDto qColdWaterFlowDto,
			QueryElEnFlow queryElEnFlow) {
		super();
		this.qAcntCountDto = qAcntCountDto;
		this.qColdWaterFlowDto = qColdWaterFlowDto;
		this.queryElEnFlow = queryElEnFlow;
	}

	/**
	 * Запрос счетчиков связанных с УСПД
	 * @param id - Room.idRoom
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<AcntCountsDTO> getAcntCounts(Integer id){
		List<AcntCountsDTO> list = qAcntCountDto.getAcntCountsDTO(id);
	return list;
	}
	
	/**
	 * Запрос по расчету расхода холодной или горячей воды 
	 * @param factoryNumberUspd
	 * @param dateFrom
	 * @param ratio
	 * @param typeCount
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<ColdWaterFlowDTO> getWaterFlowDto(String factoryNumberUspd, //"%", "2022-01%", "2021-12%", "1000" 
			                                      LocalDate dateFrom, 
			                                      String ratio, 
			                                      String typeCount){
		String dateCurr = dateFrom.toString().substring(0, 7) + "%";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%";
				
		List<ColdWaterFlowDTO> listFlowWaterCold = qColdWaterFlowDto.getQueryResult(factoryNumberUspd,
				                                                                    dateCurr, 
				                                                                    datePrev, 
				                                                                    ratio, typeCount); 
	return listFlowWaterCold;	
	}
	
	/**
	 * Запрос по расчету электро энергии
	 * @param factoryNumberUspd
	 * @param dateFrom
	 * @param ratio
	 * @param typeCount
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<ElEnFlowDTO> getElEnFlowDto(String factoryNumberUspd, LocalDate dateFrom){ //"%", "2022-01%", "2021-12%" 
			                                      
		String dateCurr = dateFrom.toString().substring(0, 7) + "%";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%";
				
		List<ElEnFlowDTO> listFlowElEn = queryElEnFlow.getQueryResult(factoryNumberUspd, dateCurr, datePrev); 
	return listFlowElEn;	
	}

}
