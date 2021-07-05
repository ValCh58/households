package eis.company.households.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eis.company.households.model.TypeObject;

public interface TypeObjectRepository extends JpaRepository<TypeObject, Integer> {
	//Optional<TypeObject> findById(Integer id);
}
