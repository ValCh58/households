package eis.company.households.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AlarmDTO {
	public AlarmDTO() {
		// TODO Auto-generated constructor stub
	}
	private Integer idAlarm;
	private Integer idMeasuring;
	private String factoryNumberUspd;
	private LocalDateTime timeStamp;
	private String msgText;
	private String active;
}
