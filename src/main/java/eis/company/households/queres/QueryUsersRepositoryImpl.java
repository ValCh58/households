package eis.company.households.queres;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Component;

import eis.company.households.dto.UsersRolesDTO;
import eis.company.households.repository.QueryUsersRepository;

@Component
public class QueryUsersRepositoryImpl implements QueryUsersRepository{
	
	public QueryUsersRepositoryImpl() {}

	@PersistenceContext(name="housingEntityManager")
    private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<UsersRolesDTO> queryUsersRoles() {
		
		@SuppressWarnings("deprecation")
		List<UsersRolesDTO> usersrolesdto = em.createNativeQuery("SELECT users.user_id, "
				                                                    + "users.last_name, "
				                                                    + "users.name, "
				                                                    + "user_name, "
				                                                    + "users.active, "
			                                                     	+ "users.email, "
				                                                    + "roles.role"
		    	                                                    + " FROM users, user_role, roles "
		    	          + "where users.user_id = user_role.user_id and user_role.role_id = roles.role_id order by users.last_name")
				.unwrap( org.hibernate.query.Query.class )
				.setResultTransformer(
					    new ResultTransformer() {
					    	private static final long serialVersionUID = -7293069291041022095L;
						
					    	@Override
					        public Object transformTuple(
					            Object[] tuple,
					            String[] aliases) {
					            return new UsersRolesDTO(
					                (Integer) tuple[0],
					                (String) tuple[1],
					                (String) tuple[2],
					                (String) tuple[3],
					                (Boolean) tuple[4],
					                (String) tuple[5],
					                (String) tuple[6]
					            );
					        }
					 
					        @SuppressWarnings("rawtypes")
							@Override
					        public List transformList(List collection) {
					            return collection;
					        }
					    }
					)
					.getResultList();
        return usersrolesdto;
	}

}
