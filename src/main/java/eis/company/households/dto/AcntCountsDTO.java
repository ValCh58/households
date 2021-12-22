package eis.company.households.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AcntCountsDTO {
	private Integer idCounts;
	private Integer idPersonAcnt;
	private String nameCount;
	private String serialNum;
	private LocalDateTime dateExpire;
	private String address;

}
