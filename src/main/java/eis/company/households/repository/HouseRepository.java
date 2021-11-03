package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
	Optional<House> findById(Integer id);

}
