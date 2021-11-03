package eis.company.households.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import eis.company.households.model.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
	Optional<Street> findById(Integer id);

}
