package eis.company.households.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class selectIdFromLinkObj {
private Integer id_link_object;
private Integer id_type_object;
}
