package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.ManagCompany;

@Repository
public interface ManagCompanyRepository extends JpaRepository<ManagCompany, Integer> {
   public Optional<ManagCompany> findById(Integer id);

public ManagCompany findByNameCompany(String name);
}
