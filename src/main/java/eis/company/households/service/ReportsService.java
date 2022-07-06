package eis.company.households.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.dto.ChartHouseApartDto;
import eis.company.households.dto.ColdWaterFlowDTO;
import eis.company.households.dto.ElEnFlowDTO;
import eis.company.households.dto.HotCountFlowDTO;
import eis.company.households.queres.QueryAcntCountsDto;
import eis.company.households.queres.QueryChartHouseApart;
import eis.company.households.queres.QueryColdWaterFlowDto;
import eis.company.households.queres.QueryElEnFlow;
import eis.company.households.queres.QueryHotCountFlowDto;


@Service
public class ReportsService {
	
	private QueryAcntCountsDto qAcntCountDto;
	private QueryColdWaterFlowDto qColdWaterFlowDto; 
	private QueryElEnFlow queryElEnFlow;
	private QueryHotCountFlowDto queryHotCountFlowDto; 
	private QueryChartHouseApart qChartHouseApart;
	
	@Autowired
	public ReportsService(QueryAcntCountsDto qAcntCountDto, QueryColdWaterFlowDto qColdWaterFlowDto,
			QueryElEnFlow queryElEnFlow, QueryHotCountFlowDto queryHotCountFlowDto, QueryChartHouseApart qChartHouseApart) {
		super();
		this.qAcntCountDto = qAcntCountDto;
		this.qColdWaterFlowDto = qColdWaterFlowDto;
		this.queryElEnFlow = queryElEnFlow;
		this.queryHotCountFlowDto = queryHotCountFlowDto;
		this.qChartHouseApart = qChartHouseApart;
	}

	/**
	 * Запрос счетчиков связанных с УСПД
	 * @param id - Room.idRoom
	 * @return list
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
	 * @return listHotCountDto
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
	 * @param typeCount
	 * @return listFlowWaterCold
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<ColdWaterFlowDTO> getWaterFlowDto(String factoryNumberUspd, //"%", "2022-01%", "2021-12%", "1000" 
			                                      LocalDate dateFrom, 
			                                      String ratio, 
			                                      String typeCount){
		String dateCurr = dateFrom.toString().substring(0, 7) + "%";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%";
				
		List<ColdWaterFlowDTO> listFlowWaterCold = 
				Optional.ofNullable(qColdWaterFlowDto.getQueryResult(factoryNumberUspd, dateCurr, datePrev, typeCount))
				                      .orElseThrow(()->new ResourceNotFoundException("Object list ColdWaterFlowDTO Not found")); 
	return listFlowWaterCold;	
	}
	
	/**
	 * Запрос на построение диаграммы по расходу холодной и горячей воды 
	 * @param dateFrom
	 * @param dateTo
	 * @param typeCount
	 * @return List<ChartHouseApartDto>
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<ChartHouseApartDto> getChartHouseApartDto(LocalDate dateFrom, LocalDate dateTo, int idLinkObj){
		String dateCurr = dateFrom.toString().substring(0, 10) + "%";
		String datePrev = dateTo.toString().substring(0, 10) + "%";
			  
		 List<ChartHouseApartDto> listChartHouseApartDto =	
				Optional.ofNullable(qChartHouseApart.getQueryChartHouseApart(datePrev, dateCurr, idLinkObj))
				                      .orElseThrow(()->new ResourceNotFoundException("Object list ChartHouseApartDto Not found"));
			
	return 	listChartHouseApartDto;
	}
		
	/**
	 * Запрос по расчету электро энергии
	 * @param factoryNumberUspd
	 * @param dateFrom
	 * @param typeCount
	 * @return listFlowElEn
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public List<ElEnFlowDTO> getElEnFlowDto(String factoryNumberUspd, LocalDate dateFrom){ //"%", "2022-01%", "2021-12%" 
			                                      
		String dateCurr = dateFrom.toString().substring(0, 7) + "%";
		String datePrev = dateFrom.minusMonths(1L).toString().substring(0, 7) + "%";
				
		List<ElEnFlowDTO> listFlowElEn = 
				Optional.ofNullable(queryElEnFlow.getQueryResult(factoryNumberUspd, dateCurr, datePrev))
				                        .orElseThrow(()->new ResourceNotFoundException("Object list ElEnFlowDTO Not found")); 
	return listFlowElEn;	
	}

}
