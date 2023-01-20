package eis.company.households.dto;

import java.util.ArrayList;
import java.util.List;

import eis.company.households.model.TypeUspd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EditUspdDTO {
	private int idUspdDev;
    private String nameUspdDev;
    private String numUspdDev;
    private String addressLoc;
    private int idCounts;
    private int idConfigUspd;
    private int idTypeUspdDev;
    private int retIdTypeUspdDev;
    @Default
    private int idLinkObject = 0;
    @Default
    private List<TypeUspd> typeUspd = new ArrayList<TypeUspd>();
    
    

}
