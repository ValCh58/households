package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.PersonAcnt;

@Repository
public interface PersonAcntRepository extends JpaRepository<PersonAcnt, Integer> {
	Optional<PersonAcnt> findById(Integer id);

}
