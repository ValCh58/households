package eis.company.households.restcontroller;

import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.dto.AlarmDTO;
import eis.company.households.dto.CountWaterDTO;
import eis.company.households.modeleis.CountElEn;
import eis.company.households.modeleis.CountHeat;
import eis.company.households.modeleis.Measuring;
import eis.company.households.modeleis.RawData;
import eis.company.households.service.ObjectAdminService;

@RestController
public class ObjectAdminRest {

	private ObjectAdminService objAdmSrv;
	private Environment env;

	@Autowired
	public ObjectAdminRest(ObjectAdminService objAdmSrv, Environment env) {
		super();
		this.objAdmSrv = objAdmSrv;
		this.env = env;
	}
	
	/**
	 * Making a URL to call the report
	 * @param pathReport
	 * @param numUspd
	 * @param dateFrom
	 * @param dateTo
	 * @return URL
	 */
	private String makeUrlReport(String pathReport, String numUspd, 
			                     LocalDate dateFrom, LocalDate dateTo) {
		
		StringBuilder sbUrl = new StringBuilder("http://");
		sbUrl.append(env.getProperty("jasperreport.ip_port"));//application.properties
		sbUrl.append("/jasperserver/flow.html?_flowId=viewReportFlow&reportUnit=");
		sbUrl.append(pathReport); 
		sbUrl.append(env.getProperty("jasperreport.type.report"));//application.properties
		sbUrl.append("&j_username=");
		sbUrl.append(env.getProperty("jasperreport.username"));//application.properties
		sbUrl.append("&j_password=");
		sbUrl.append(env.getProperty("jasperreport.password"));//application.properties
		sbUrl.append("&dateBegin=");
		sbUrl.append(dateFrom.toString());
		sbUrl.append("&dateEnd=");
		sbUrl.append(dateTo.toString());
		if(!numUspd.equals("0")) {
		  sbUrl.append("&numUspd=");
		  sbUrl.append(numUspd);
		}
		
		return sbUrl.toString();
	}

	/**
	 * Print of PDF reports
	 * Calling the page of the report of objects
	 */
	@GetMapping(value = "/admin/repUspd/numUspd/{numUspd}/dateFrom/{dateFrom}/dateTo/{dateTo}")
	public ResponseEntity<String> getUspdObjectsForPDF(@PathVariable("numUspd") String numUspd,
			    @PathVariable("dateFrom") LocalDate dateFrom, @PathVariable("dateTo") LocalDate dateTo) {
		String url = null;
        
		if(!numUspd.equals("0"))
		    url = Optional.ofNullable(makeUrlReport("/reports/Housing/admin/uspd&output=", numUspd, dateFrom, dateTo))
				      .orElseThrow(()->new ResourceNotFoundException("Url is invalid"));
		else
			url = Optional.ofNullable(makeUrlReport("/reports/Housing/admin/uspd2&output=", numUspd, dateFrom, dateTo))
		      .orElseThrow(()->new ResourceNotFoundException("Url is invalid"));
			
		
		return ResponseEntity.status(OK).body(url);
	}
	

	/**
	 * REST USPD filtration
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
	 * REST Counters, filtering
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
	 * REST Electric meters, filtration
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
	 * REST Row data from servers, filtering
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
	 * REST Alarm messages
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
	 * REST Heat meters filtration
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
