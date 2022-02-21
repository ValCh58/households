package eis.company.households.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.dto.ElEnFlowDTO;
import eis.company.households.dto.HotCountFlowDTO;
import eis.company.households.queres.QueryAcntCountsDto;
import eis.company.households.queres.QueryColdWaterFlowDto;
import eis.company.households.queres.QueryElEnFlow;
import eis.company.households.queres.QueryHotCountFlowDto;


@Service
public class ReportsService {
	
	private QueryAcntCountsDto qAcntCountDto;
	private QueryColdWaterFlowDto qColdWaterFlowDto; 
	private QueryElEnFlow queryElEnFlow;
	private QueryHotCountFlowDto queryHotCountFlowDto; 
	
	@Autowired
	public ReportsService(QueryAcntCountsDto qAcntCountDto, QueryColdWaterFlowDto qColdWaterFlowDto,
			QueryElEnFlow queryElEnFlow, QueryHotCountFlowDto queryHotCountFlowDto) {
		super();
		this.qAcntCountDto = qAcntCountDto;
		this.qColdWaterFlowDto = qColdWaterFlowDto;
		this.queryElEnFlow = queryElEnFlow;
		this.queryHotCountFlowDto = queryHotCountFlowDto;
	}

	/**
	 * Запрос счетчиков связанных с УСПД
	 * @param id - Room.idRoom
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<AcntCountsDTO> getAcntCounts(Integer id){
		List<AcntCountsDTO> list = Optional.ofNullable(qAcntCountDto.getAcntCountsDTO(id))
				                           .orElseThrow(()->new ResourceNotFoundException("Object list AcntCountsDTO Not found"));
	return list;
	}
	
	/**
	 * Запрос по расчету расхода тепловой энергии 
	 * @param factoryNumberUspd
	 * @param dateFrom
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<HotCountFlowDTO> getHotCountDto(String factoryNumberUspd, LocalDate dateFrom){
		String dateCurr = dateFrom.toString().substring(0, 7) + "%";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%";
		
		List<HotCountFlowDTO> listHotCountDto = Optional.ofNullable(queryHotCountFlowDto.getQueryResult(factoryNumberUspd, dateCurr, datePrev))
				                               .orElseThrow(()->new ResourceNotFoundException("Object list HotCountFlowDTO Not found"));
		return listHotCountDto;
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
				
		List<ColdWaterFlowDTO> listFlowWaterCold = Optional.ofNullable(qColdWaterFlowDto.getQueryResult(factoryNumberUspd,
				                                                                    dateCurr, 
				                                                                    datePrev, 
				                                                                    ratio, typeCount))
				                                  .orElseThrow(()->new ResourceNotFoundException("Object list ColdWaterFlowDTO Not found")); 
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
				
		List<ElEnFlowDTO> listFlowElEn = Optional.ofNullable(queryElEnFlow.getQueryResult(factoryNumberUspd, dateCurr, datePrev))
				                        .orElseThrow(()->new ResourceNotFoundException("Object list ElEnFlowDTO Not found")); 
	return listFlowElEn;	
	}

}
