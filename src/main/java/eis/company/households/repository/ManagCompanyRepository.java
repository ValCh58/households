package eis.company.households.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eis.company.households.model.ManagCompany;

public interface ManagCompanyRepository extends JpaRepository<ManagCompany, Integer> {
//public Optional<ManagCompany> findById(Integer id);
}
