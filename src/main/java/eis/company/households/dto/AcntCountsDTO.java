package eis.company.households.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AcntCountsDTO {
	private Integer idCounts;
	private Integer idPersonAcnt;
	private String nameCount;
	private String serialNum;
	private LocalDate dateExpire;
	private String address;

}
