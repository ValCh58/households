package eis.company.households.dto;

import java.time.LocalDate;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ElEnFlowDTO {
	private LocalDateTime timeStamp;
	private String addressLoc;
	private String numAcnt;
	private String nameCount;
	private Double tarifEn;   //текущий расход
	private Double prevTarifEn;//предыдущий расход
	private Double diffTarifEn;//разница 
	private String serialNum;
	private LocalDate dateExpire;
	private String factoryNumberUspd;
	private String typeTarif;//T1 = 0 or T2 = 1
}
