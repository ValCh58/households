package eis.company.households.dto;

import java.time.LocalDate;
import eis.company.households.MyConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CountsDTO {
	
	private int idCounts;
	@Default
    private int typeCount = MyConst.COOL_WATER_COUNT;
    private String serialNum;
    private LocalDate datePlug;
    private LocalDate dateExpire;
    private String nameCount;
    private String address;
    private Integer numCh;
    private Integer numRat;
    @Default
	private int idLinkObject = 0;

}
