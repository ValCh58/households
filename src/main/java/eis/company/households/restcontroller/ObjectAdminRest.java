package eis.company.households.restcontroller;

import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.dto.AlarmDTO;
import eis.company.households.dto.CountWaterDTO;
import eis.company.households.modeleis.CountElEn;
import eis.company.households.modeleis.CountHeat;
import eis.company.households.modeleis.Measuring;
import eis.company.households.modeleis.RawData;
import eis.company.households.service.ObjectAdminService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class ObjectAdminRest {

	private ObjectAdminService objAdmSrv;

	@Autowired
	public ObjectAdminRest(ObjectAdminService objAdmSrv) {
		super();
		this.objAdmSrv = objAdmSrv;
	}

	/**
	 * REST Print of PDF reports
	 * 
	 * 
	 */
	@GetMapping(value = "/reports")
	public ResponseEntity<byte[]> getUspdObjets() {
	  try {
			List<Measuring> list = objAdmSrv.retUspdObj();

			// dynamic parameters required for report
			Map<String, Object> uspdParams = new HashMap<String, Object>();
			uspdParams.put("employeeData", new JRBeanCollectionDataSource(list));
			JasperPrint uspdReport = JasperFillManager.fillReport(
						             JasperCompileManager.compileReport(
							                ResourceUtils.getFile("classpath:/reports/uspd.jrxml")
									                     .getAbsolutePath()) // path of the jasper report
						             , uspdParams // dynamic parameters
							         , new JREmptyDataSource() );

			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "uspd.pdf");
			//create the report in PDF format
			return new ResponseEntity<byte[]> (JasperExportManager.exportReportToPdf(uspdReport), headers, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * REST УСПД фильтрация
	 * 
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_uspd/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public ResponseEntity<List<Measuring>> updateViewUspdPar(@PathVariable("numUspd") String numUspd,
			@PathVariable("dateFrom") LocalDate dateFrom, @PathVariable("dateTo") LocalDate dateTo) {

		return ResponseEntity.status(OK).body(objAdmSrv.retUpdateUspdObj(numUspd, dateFrom, dateTo));

	}

	/**
	 * REST Счетчики фильтрация
	 * 
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_count/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public ResponseEntity<List<CountWaterDTO>> updateViewCountWaterPar(@PathVariable("numUspd") String numUspd,
			@PathVariable("dateFrom") LocalDate dateFrom, @PathVariable("dateTo") LocalDate dateTo) {

		return ResponseEntity.status(OK).body(objAdmSrv.retUpdateCountWaterObj(numUspd, dateFrom, dateTo));
	}

	/**
	 * REST Эл. счетчики фильтрация
	 * 
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_count_el/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public ResponseEntity<List<CountElEn>> updateViewCountElEnPar(@PathVariable("numUspd") String numUspd,
			@PathVariable("dateFrom") LocalDate dateFrom, @PathVariable("dateTo") LocalDate dateTo) {

		return ResponseEntity.status(OK).body(objAdmSrv.retUpdateCountElEnObj(numUspd, dateFrom, dateTo));
	}

	/**
	 * REST Row данные от серверов, фильтрация
	 * 
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/monit_rawdata/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public ResponseEntity<List<RawData>> updateViewRawData(@PathVariable("numUspd") String numUspd,
			@PathVariable("dateFrom") LocalDate dateFrom, @PathVariable("dateTo") LocalDate dateTo) {

		return ResponseEntity.status(OK).body(objAdmSrv.retUpdateRawData(numUspd, dateFrom, dateTo));

	}

	/**
	 * REST Аварийные сообщения
	 * 
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/message_obj/idAlarm/{idAlarm}")
	public ResponseEntity<AlarmDTO> updateAlarm(@PathVariable("idAlarm") Integer idAlarm) {

		return ResponseEntity.status(OK).body(objAdmSrv.retUpdAlarmDTO(idAlarm));
	}

	/**
	 * REST Тепл. счетчики фильтрация
	 * 
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return list
	 */
	@GetMapping(value = "/admin/object_count_he/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public ResponseEntity<List<CountHeat>> updateViwHeaterData(@PathVariable("numUspd") String numUspd,
			@PathVariable("dateFrom") LocalDate dateFrom, @PathVariable("dateTo") LocalDate dateTo) {

		return ResponseEntity.status(OK).body(objAdmSrv.retUpdCountHeater(numUspd, dateFrom, dateTo));
	}

}
