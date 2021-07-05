package eis.company.households.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServerObjDTO {
	private int idComServer;
	private String nameServer;
	private String ipServer;
	private String portServer;
	private String nameCompany;
	@Default
	private int statusSrv = 0;//0-not connect:1-yes connect//
	
}
