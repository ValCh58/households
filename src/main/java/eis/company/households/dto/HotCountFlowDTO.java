package eis.company.households.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class HotCountFlowDTO {
	private LocalDateTime timeStamp;
	private String addressLoc;
	private String numAcnt;
	private String nameCount;
	private Double currGKalor;   //текущий расход
	private Double prevGKalor;//предыдущий расход
	private Double diffGKalor;//разница 
	private String serialNum;
	private LocalDate dateExpire;
	private String factoryNumberUspd;
	private Integer typeCount;
}
