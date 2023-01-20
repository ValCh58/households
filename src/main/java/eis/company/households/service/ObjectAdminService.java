package eis.company.households.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.Exceptions.SaveResourceErrorException;
import eis.company.households.dto.AlarmDTO;
import eis.company.households.dto.CountWaterDTO;
import eis.company.households.modeleis.Alarm;
import eis.company.households.modeleis.CountElEn;
import eis.company.households.modeleis.CountHeat;
import eis.company.households.modeleis.Measuring;
import eis.company.households.modeleis.RawData;
import eis.company.households.queres.QueryAlarmDto;
import eis.company.households.queres.QueryCountWaterDto;
import eis.company.households.queres.RetCountWaterDTOPar2;
import eis.company.households.repoeisystems.CountElEnRepository;
import eis.company.households.repoeisystems.CountHeaterRepository;
import eis.company.households.repoeisystems.MeasuringRepository;
import eis.company.households.repoeisystems.RawDataRepository;
import eis.company.households.repoeisystems.RepoAlarm;
import eis.company.households.repoeisystems.RepositoryMeasuringFilter;

@Service
public class ObjectAdminService {

	private MeasuringRepository measuringRepo;
	private CountElEnRepository countElEnRepo;
	private RetCountWaterDTOPar2 countWaterPar2;
	private RepositoryMeasuringFilter repoMeasuringFiltr;
	private RawDataRepository repoRawData;
	private QueryAlarmDto querAlarmDto;
	private QueryAlarmDto queryAlarm;
	private RepoAlarm repoAlarm;
	private QueryCountWaterDto queryCountWater;
	private CountHeaterRepository countHeaterRepo;
		
	@Autowired
	public ObjectAdminService(MeasuringRepository measuringRepo, CountElEnRepository countElEnRepo,
			RetCountWaterDTOPar2 countWaterPar2, RepositoryMeasuringFilter repoMeasuringFiltr,
			RawDataRepository repoRawData, QueryAlarmDto querAlarmDto, QueryAlarmDto queryAlarm, RepoAlarm repoAlarm,
			QueryCountWaterDto queryCountWater, CountHeaterRepository countHeaterRepo) {
		super();
		this.measuringRepo = measuringRepo;
		this.countElEnRepo = countElEnRepo;
		this.countWaterPar2 = countWaterPar2;
		this.repoMeasuringFiltr = repoMeasuringFiltr;
		this.repoRawData = repoRawData;
		this.querAlarmDto = querAlarmDto;
		this.queryAlarm = queryAlarm;
		this.repoAlarm = repoAlarm;
		this.queryCountWater = queryCountWater;
		this.countHeaterRepo = countHeaterRepo;
	}

	/**
	 * Service repository УСПД 
	 * @return List<Measuring>
	 */

	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<Measuring> retUspdObj() {
		List<Measuring> list = Optional.ofNullable(measuringRepo.findTop1000ByOrderByTimeStampDesc())
				                       .orElseThrow(()->new ResourceNotFoundException("Object list Measuring Not found"));
		return list;
	}
	
	/**
	 * Service repository УСПД фильтрация
	 * @return List<Measuring>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<Measuring> retUpdateUspdObj(String numUspd, LocalDate dateFrom, LocalDate dateTo) {
		LocalDateTime from = dateFrom.atStartOfDay();
		LocalDate dayTo = dateTo.plusDays(1);
		LocalDateTime to = dayTo.atStartOfDay();
		List<Measuring> list;
		Sort sort = Sort.by("timeStamp").descending();
		if (numUspd.equals("0")) {
			list = Optional.ofNullable(repoMeasuringFiltr.findFirst1000ByTimeStampBetween(from, to, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter1 list Measuring Not found"));
		} else {
			list = Optional.ofNullable(repoMeasuringFiltr.findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(from, to, numUspd, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter2 list Measuring Not found"));
		}
		return list;
	}
	
	/**
	 * Service repository фильтрация счетчиков воды
	 * @return List<CountWaterDTO>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountWaterDTO> retUpdateCountWaterObj(String numUspd, LocalDate dateFrom, LocalDate dateTo) {
		LocalDateTime from = dateFrom.atStartOfDay();
		LocalDate dayTo = dateTo.plusDays(1);
		LocalDateTime to = dayTo.atStartOfDay();
		List<CountWaterDTO> list = Optional.ofNullable(countWaterPar2.retCountWaterDTOPar2(numUspd, from, to))
				                           .orElseThrow(()->new ResourceNotFoundException("Object filter list CountWaterDTO Not found"));
			
		return list;
	}
	
	/**
	 * Service DTO чтение показаний счетчиков воды
	 * @return List<CountWaterDTO>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountWaterDTO> retCountWater() {
		//List<CountWater> list = countWaterRepo.findFirst1000ByOrderByTimeStampDesc();
		List<CountWaterDTO> list = Optional.ofNullable(queryCountWater.retCountWaterDTO())
				                           .orElseThrow(()->new ResourceNotFoundException("Object list CountWaterDTO Not found"));
		return list;
	}
	
	/**
	 * Service repository  счетчики эл энергии
	 * @return List<CountElEn
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountElEn> retCountElEn() {
		
		List<CountElEn> list = Optional.ofNullable(countElEnRepo.findFirst1000ByOrderByTimeStampDesc())
				                       .orElseThrow(()->new ResourceNotFoundException("Object list CountElEn Not found"));
		return list;
	}
	
	/**
	 * Service repository raw data
	 * @return List<RawData>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<RawData> retRawData(){
		List<RawData> list = Optional.ofNullable(repoRawData.findFirst1000ByOrderByEventTimeDesc())
				                     .orElseThrow(()->new ResourceNotFoundException("Object list RawData Not found"));
		return list;
	}
	
	/**
	 * Service DTO аварийные сообщения
	 * @return List<AlarmDTO>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly=true)
	public List<AlarmDTO> retAlarmMsg(){
		List<AlarmDTO> list = Optional.ofNullable(querAlarmDto.retAlarmDto())
				                      .orElseThrow(()->new ResourceNotFoundException("Object list AlarmDTO Not found"));
		return list;
	}
	
	/**
	 * Service repository счетчики эл энергии фильтрация
	 * @return List<CountElEn>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountElEn> retUpdateCountElEnObj(String numUspd, LocalDate dateFrom, LocalDate dateTo) {
		LocalDateTime from = dateFrom.atStartOfDay();
		LocalDate dayTo = dateTo.plusDays(1);
		LocalDateTime to = dayTo.atStartOfDay();
		List<CountElEn> list;
		Sort sort = Sort.by("timeStamp").descending();
		if (numUspd.equals("0")) {
			list = Optional.ofNullable(countElEnRepo.findFirst1000ByTimeStampBetween(from, to, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter1 list CountElEn Not found"));
		} else {
			list = Optional.ofNullable(countElEnRepo.findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(from, to, numUspd, sort))
                			.orElseThrow(()->new ResourceNotFoundException("Object filter2 list CountElEn Not found"));
		}
		
		return list;
	}
	
	/**
	 * Service repository счетчики тепла фильтрация
	 * @return List<CountHeat>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly=true)
	public List<CountHeat> retUpdCountHeater(String numUspd, LocalDate dateFrom, LocalDate dateTo){
		LocalDateTime from = dateFrom.atStartOfDay();
		LocalDate dayTo = dateTo.plusDays(1);
		LocalDateTime to = dayTo.atStartOfDay();
		List<CountHeat> list;
		Sort sort = Sort.by("timeStamp").descending();
		if (numUspd.equals("0")) {
			list = Optional.ofNullable(countHeaterRepo.findFirst1000ByTimeStampBetween(from, to, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter1 list CountHeat Not found"));
		} else {
			list = Optional.ofNullable(countHeaterRepo.findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(from, to, numUspd, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter1 list CountHeat Not found"));
		}
		return list;
	}
	
	/**
	 * Service repository счетчики тепла
	 * @return List<CountHeat>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountHeat> retCountHeater(){
		List<CountHeat> list = Optional.ofNullable(countHeaterRepo.findFirst1000ByOrderByTimeStampDesc())
				                       .orElseThrow(()->new ResourceNotFoundException("Object list CountHeat Not found"));
		return list;
	}
	
	/**
	 * Service repository raw data
	 * @return List<RawData>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<RawData> retUpdateRawData(String numUspd, LocalDate dateFrom, LocalDate dateTo) {
		LocalDateTime from = dateFrom.atStartOfDay();
		LocalDate dayTo = dateTo.plusDays(1);
		LocalDateTime to = dayTo.atStartOfDay();
		List<RawData> list;
		Sort sort = Sort.by("eventTime").descending();
		if (numUspd.equals("0")) {
			list = Optional.ofNullable(repoRawData.findFirst1000ByEventTimeBetween(from, to, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter1 list RawData Not found"));
		} else {
			list = Optional.ofNullable(repoRawData.findFirst1000ByEventTimeBetweenAndNumUspd(from, to, numUspd, sort))
					       .orElseThrow(()->new ResourceNotFoundException("Object filter2 list RawData Not found"));
		}
		return list;
	}
	
	/**
	 * Service DTO сообщения об авариях
	 * @return AlarmDTO
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager")
	public AlarmDTO retUpdAlarmDTO(Integer idalarm) {
		Optional<Alarm> opt = repoAlarm.findById(idalarm);
		Alarm al = opt.orElseThrow(()->new ResourceNotFoundException("Object Alarm:" + idalarm.toString() + " Not found"));
		al.setActive("1");
		al = Optional.ofNullable(repoAlarm.save(al))
				     .orElseThrow(()->new SaveResourceErrorException("Save resource error Alarm"));
		AlarmDTO ad = Optional.ofNullable(queryAlarm.retAlarmDto(idalarm))
				              .orElseThrow(()->new SaveResourceErrorException("Request execution error for AlarmDTO"));
			
		return ad;
	}

}
