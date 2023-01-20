package eis.company.households.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EditServerDTO {
	private Integer id_com_server;
	private String name_server;
	private String ip_server;
	private String port_server;
	private String name_company;
	private String name_type;
	private Integer id_manag_company;
	private Integer id_type_object;
	
}
