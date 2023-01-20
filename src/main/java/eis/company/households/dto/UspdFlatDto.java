package eis.company.households.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class UspdFlatDto {
	private int id_uspd_dev;
	private String name_uspd_dev;
	private String address_loc;
	private int id_com_server;
	private String num_uspd_dev;
	private String name_server;

}
