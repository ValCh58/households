package eis.company.households.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author ChVal
 *
 */
@Data
@Builder
@AllArgsConstructor

public class LinkObjectDTO {
private Integer id_link_object;
private Integer id_parent;
private String name_object;
private String prop_object;
private Integer id_object;
private Integer id_type_object;
}
