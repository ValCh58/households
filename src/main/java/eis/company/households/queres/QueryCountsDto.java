package eis.company.households.queres;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import eis.company.households.dto.CountsDTO;

@Component
public class QueryCountsDto {

	@PersistenceContext(name="housingEntityManager")
    private EntityManager em;
	public CountsDTO retCountsDto(Integer id){
	
	TypedQuery<Tuple> q = em.createQuery(
            "SELECT c.idCounts as idCounts, c.serialNum as serialNum, "
            + "c.datePlug as datePlug, c.dateExpire as dateExpire, c.nameCount as nameCount, c.address as address, "
            + "c.numCh as numCh, c.numRat as numRat, c.typeCount as typeCount"
            + " FROM counts c where c.idCounts = :id", Tuple.class).setParameter("id", id);
	
	Optional<Tuple> counts = Optional.ofNullable(q.getSingleResult());
	Tuple t = counts.isPresent() ? counts.get() : null;
	if(t == null) {return null;	}
	
	int linkObj = 0;
	
	CountsDTO cdto = new CountsDTO((int)t.get("idCounts"),
		                           (int)t.get("typeCount"), 
		                           (String)t.get("serialNum"), 
		                           (LocalDate)t.get("datePlug"), 
		                           (LocalDate)t.get("dateExpire"),
		                           (String)t.get("nameCount"), 
		                           (String)t.get("address"),
		                           (int)t.get("numCh"),
		                           (int)t.get("numRat"),
		                           (int)linkObj);
	
	return  cdto;
	}
	
}
