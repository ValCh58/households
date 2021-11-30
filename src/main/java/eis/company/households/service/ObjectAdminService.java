package eis.company.households.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired private MeasuringRepository measuringRepo;
	@Autowired private CountElEnRepository countElEnRepo;
	@Autowired private RetCountWaterDTOPar2 countWaterPar2;
	@Autowired private RepositoryMeasuringFilter repoMeasuringFiltr;
	@Autowired private RawDataRepository repoRawData;
	@Autowired private QueryAlarmDto querAlarmDto;
	@Autowired private QueryAlarmDto queryAlarm;
	@Autowired private RepoAlarm repoAlarm;
	@Autowired private QueryCountWaterDto queryCountWater;
	@Autowired private CountHeaterRepository countHeaterRepo;
	
	/**
	 * Service repository УСПД 
	 * @return List<Measuring>
	 */

	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<Measuring> retUspdObj() {
		// List<Measuring> list = measuringRepo.findAll();
		// TypedSort<Measuring> measuring = Sort.sort(Measuring.class);
		// Sort sort = measuring.by(Measuring::getTimeStamp).ascending();
		// List<Measuring> list = measuringRepo.findFirst1000ByOrderByTimeStamp(sort);
		// List<Measuring> list = measuringRepo.findFirst1000ByOrderByTimeStampAsc();
		// List<Measuring> list = measuringRepo.findFirst1000ByOrderByTimeStampDesc();
		List<Measuring> list = measuringRepo.findTop1000ByOrderByTimeStampDesc();
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
			list = repoMeasuringFiltr.findFirst1000ByTimeStampBetween(from, to, sort);
		} else {
			list = repoMeasuringFiltr.findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(from, to, numUspd, sort);
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
		List<CountWaterDTO> list = countWaterPar2.retCountWaterDTOPar2(numUspd, from, to);
			
		return list;
	}
	
	/**
	 * Service DTO чтение показаний счетчиков воды
	 * @return List<CountWaterDTO>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountWaterDTO> retCountWater() {
		//List<CountWater> list = countWaterRepo.findFirst1000ByOrderByTimeStampDesc();
		List<CountWaterDTO> list = queryCountWater.retCountWaterDTO();
		return list;
	}
	
	/**
	 * Service repository  счетчики эл энергии
	 * @return List<CountElEn
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountElEn> retCountElEn() {
		// List<CountElEn> list = countElEnRepo.findAll();
		List<CountElEn> list = countElEnRepo.findFirst1000ByOrderByTimeStampDesc();
		return list;
	}
	
	/**
	 * Service repository raw data
	 * @return List<RawData>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<RawData> retRawData(){
		List<RawData> list = repoRawData.findFirst1000ByOrderByEventTimeDesc();
		return list;
	}
	
	/**
	 * Service DTO аварийные сообщения
	 * @return List<AlarmDTO>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly=true)
	public List<AlarmDTO> retAlarmMsg(){
		List<AlarmDTO> list = querAlarmDto.retAlarmDto();
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
			list = countElEnRepo.findFirst1000ByTimeStampBetween(from, to, sort);
		} else {
			list = countElEnRepo.findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(from, to, numUspd, sort);
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
			list = countHeaterRepo.findFirst1000ByTimeStampBetween(from, to, sort);
		} else {
			list = countHeaterRepo.findFirst1000ByTimeStampBetweenAndFactoryNumberUspd(from, to, numUspd, sort);
		}
		return list;
	}
	
	/**
	 * Service repository счетчики тепла
	 * @return List<CountHeat>
	 */
	@Transactional(transactionManager = "eisystemsTransactionManager", readOnly = true)
	public List<CountHeat> retCountHeater(){
		List<CountHeat> list = countHeaterRepo.findFirst1000ByOrderByTimeStampDesc();
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
			list = repoRawData.findFirst1000ByEventTimeBetween(from, to, sort);
		} else {
			list = repoRawData.findFirst1000ByEventTimeBetweenAndNumUspd(from, to, numUspd, sort);
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
		if(opt.isEmpty()) {return new AlarmDTO();}
		Alarm al = opt.get();
		al.setActive("1");
		if(repoAlarm.save(al) == null) {return new AlarmDTO();}
		AlarmDTO ad = queryAlarm.retAlarmDto(idalarm);
		if(ad == null) return new AlarmDTO();
		return ad;
	}

}
