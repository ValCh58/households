package eis.company.households.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColdWaterFlowDTO {

	private LocalDateTime timeStamp;
	private String addressLoc;
	private String numAcnt;
	private String nameCount;
	private Double countW;   //текущий расход
	private Double prevCountW;//предыдущий расход
	private Double diffCountW;//разница 
	private String serialNum;
	private LocalDate dateExpire;
	private String factoryNumberUspd;
	private Integer numCh;
}
