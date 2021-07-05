package eis.company.households.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eis.company.households.model.Counts;

public interface CountsRepository extends JpaRepository<Counts, Integer> {

	//public Optional<Counts> findById(Integer id);
}
