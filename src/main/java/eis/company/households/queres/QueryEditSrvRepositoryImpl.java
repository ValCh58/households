package eis.company.households.queres;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Component;

import eis.company.households.dto.EditServerDTO;
import eis.company.households.repository.QueryEditSrvRepository;
//Переделать!!!
@Component
public class QueryEditSrvRepositoryImpl implements QueryEditSrvRepository {
	
	public QueryEditSrvRepositoryImpl() {}
	
	@PersistenceContext(name="housingEntityManager")
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EditServerDTO> queryEditModalFormRepository(Integer idComSrv) {
		
		@SuppressWarnings("deprecation")
		List<EditServerDTO> editServer = em.createNativeQuery(
				"select id_com_server, \r\n"
				+ "cs.name_server, \r\n"
				+ "cs.ip_server, \r\n"
				+ "cs.port_server, \r\n"
				+ "(select name_company from manag_company where id_manag_company = cs.id_manag_company) as name_company,\r\n"
				+ "(select name_type from type_object where id_type_object = cs.id_type_object) as name_type,\r\n"
				+ "cs.id_manag_company, \r\n"
				+ "cs.id_type_object\r\n"
				+ "from com_server cs \r\n"
				+ "where cs.id_com_server = " + idComSrv
				).unwrap( org.hibernate.query.Query.class )
			.setResultTransformer(
				    new ResultTransformer() {
				    	private static final long serialVersionUID = -7293069291041022077L;
					
				    	@Override
				        public Object transformTuple(
				            Object[] tuple,
				            String[] aliases) {
				            return new EditServerDTO(
				            	((Number) tuple[0]).intValue(),
				                (String)  tuple[1],
				                (String)  tuple[2],
				                (String)  tuple[3],
				                (String)  tuple[4],
				                (String)  tuple[5], 
				                ((Number)  tuple[6]).intValue(),
				                ((Number)  tuple[7]).intValue()
				            );
				        }
				    	@SuppressWarnings("rawtypes")
						@Override
				        public List transformList(List collection) {
				            return collection;
				        }
				    }
				).getResultList();
		
		return editServer;
	}

}
