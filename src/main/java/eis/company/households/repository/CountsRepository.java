package eis.company.households.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.Counts;

@Repository
public interface CountsRepository extends JpaRepository<Counts, Integer> {

	//public Optional<Counts> findById(Integer id);
}
