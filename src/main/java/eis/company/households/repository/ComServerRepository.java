package eis.company.households.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.ComServer;

@Repository
public interface ComServerRepository extends JpaRepository<ComServer, Integer> {

	//Optional<ComServer> findById(Integer id);
}
