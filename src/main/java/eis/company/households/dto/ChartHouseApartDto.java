package eis.company.households.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartHouseApartDto {
	
	private LocalDateTime timeStamp;
	private String room;
	private String numAcnt;
	private String nameCount;
	private Double diffCountW;//разница 
	private Integer typeCount;
}
