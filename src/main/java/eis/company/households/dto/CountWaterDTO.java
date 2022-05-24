package eis.company.households.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CountWaterDTO {
	private int idCountWater;
	private String factoryNumberUspd;
	private int id_Measuring;
	private BigDecimal countW;
	private LocalDateTime timeStamp;
	private int typeCount;
	private int numCh;
	private int numRat;
}
