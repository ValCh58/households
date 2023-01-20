package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.LinkObject;

@Repository
public interface LinkObjectRepository extends JpaRepository<LinkObject, Integer> {

	//public Optional<LinkObject> findById(Integer id);
	public Optional<LinkObject> findByIdParent(Integer id);
}
