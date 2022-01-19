package eis.company.households.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ColdWaterFlowDTO {

	private LocalDate timeStamp;
	private String addressLoc;
	private String numAcnt;
	private String nameCount;
	private Double countW;
	private String serialNum;
	private LocalDate dateExpire;
	private String factoryNumberUspd;
	private Integer numCh;
}
