package eis.company.households.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.TypeObject;

@Repository
public interface TypeObjectRepository extends JpaRepository<TypeObject, Integer> {

	
	//Optional<TypeObject> findById(Integer id);

	TypeObject findByNameType(String string);
}
