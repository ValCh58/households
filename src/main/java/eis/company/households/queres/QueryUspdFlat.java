package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import org.springframework.stereotype.Component;

import eis.company.households.dto.UspdFlatDto;

@Component
public class QueryUspdFlat {
	
	@PersistenceContext(name = "housingEntityManager")
    private EntityManager em;
	public List<UspdFlatDto> retUspdFlatDto(){
	
	List<UspdFlatDto> listDto = new ArrayList<>();	
		
	@SuppressWarnings("unchecked")
	List<Tuple> q = em.createNativeQuery(
            "select ud.id_uspd_dev, ud.name_uspd_dev, ud.address_loc, ud.id_com_server, ud.num_uspd_dev,\r\n"
            + " (select name_server from com_server where id_com_server = ud.id_com_server) as name_server\r\n"
            + " from uspd_dev ud", Tuple.class).getResultList();
	
	if(q == null) {return null;	}
	for(Tuple t : q) {
	
	UspdFlatDto ufdto = new UspdFlatDto((int)t.get("id_uspd_dev"),
		                               (String)t.get("name_uspd_dev"), 
		                               (String)t.get("address_loc"), 
		                               (int)t.get("id_com_server"), 
		                               (String)t.get("num_uspd_dev"),
		                               (String)t.get("name_server"));
	listDto.add(ufdto);
	}
	
	return  listDto;
	}

}
