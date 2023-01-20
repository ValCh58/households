package eis.company.households.utility;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import eis.company.households.Exceptions.ResourceNotFoundException;

@Component
public class ReportUtils {
	private Environment env;
	
	public ReportUtils(Environment env) {
		super();
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
	private String makeUrlReport(String pathReport, String numUspd, LocalDate dateFrom, LocalDate dateTo) {
		
		StringBuilder sbUrl = new StringBuilder("http://");
		sbUrl.append(env.getProperty("jasperreport.ip_port"));//application.properties
		sbUrl.append("/jasperserver/flow.html?_flowId=viewReportFlow&reportUnit=");
		sbUrl.append(pathReport); 
		sbUrl.append(env.getProperty("jasperreport.type.report"));//application.properties
		sbUrl.append("&j_username=");
		sbUrl.append(env.getProperty("jasperreport.username"));//application.properties
		sbUrl.append("&j_password=");
		sbUrl.append(env.getProperty("jasperreport.password"));//application.properties
		
		StringBuilder sbParam = new StringBuilder();
		sbParam.append("&dateBegin=");
		sbParam.append(dateFrom.toString());
		sbParam.append("&dateEnd=");
		sbParam.append(dateTo.toString());
		
		if(!numUspd.equals("0")) {
		  sbParam.append("&numUspd=");
		  sbParam.append(numUspd);
		}
		
		return sbUrl.append(sbParam).toString();
	}
	
	/**
	 * Resource URL preparation
	 * 
	 */
	@SuppressWarnings("unused")
	public String prepUrl(String pathReport, String numUspd, LocalDate dateFrom, LocalDate dateTo) {
				
		String url = Optional.ofNullable(makeUrlReport(pathReport, numUspd, dateFrom, dateTo))
				      .orElseThrow(()->new ResourceNotFoundException("Url is invalid"));
		return url;
	}
	
@SuppressWarnings("unused")
private String makeUrlReport(String pathReport, String numUspd, String dateFrom, String dateTo) {
		
		StringBuilder sbUrl = new StringBuilder("http://");
		sbUrl.append(env.getProperty("jasperreport.ip_port"));//From application.properties
		sbUrl.append("/jasperserver/flow.html?_flowId=viewReportFlow&reportUnit=");
		sbUrl.append(pathReport); 
		sbUrl.append(env.getProperty("jasperreport.type.report"));//From application.properties
		sbUrl.append("&j_username=");
		sbUrl.append(env.getProperty("jasperreport.username"));//From application.properties
		sbUrl.append("&j_password=");
		sbUrl.append(env.getProperty("jasperreport.password"));//From application.properties
		
		StringBuilder sbParam = new StringBuilder();
		sbParam.append("&dateBegin=");
		sbParam.append(dateFrom);
		sbParam.append("&dateEnd=");
		sbParam.append(dateTo);
		
		if(!numUspd.equals("0")) {
		  sbParam.append("&numUspd=");
		  sbParam.append(numUspd);
		}
		
		return sbUrl.append(sbParam).toString();
	}

/**
 * Resource URL preparation
 * 
 */
@SuppressWarnings("unused")
public String prepUrl(String pathReport, String numUspd, String dateFrom, String dateTo) {
			
	String url = Optional.ofNullable(makeUrlReport(pathReport, numUspd, dateFrom, dateTo))
			      .orElseThrow(()->new ResourceNotFoundException("Url is invalid"));
	return url;
}    

}
