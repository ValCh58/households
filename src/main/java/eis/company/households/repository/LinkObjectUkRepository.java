package eis.company.households.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.LinkObjectUk;

@Repository
public interface LinkObjectUkRepository extends JpaRepository<LinkObjectUk, Integer> {
	public Optional<List<LinkObjectUk>> findByIdParent(Integer id);

}
