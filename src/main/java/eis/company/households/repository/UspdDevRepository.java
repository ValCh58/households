/**
 * 
 */
package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eis.company.households.model.UspdDev;

/**
 * @author Admin
 * @param <T>
 *
 */
public interface UspdDevRepository extends JpaRepository<UspdDev, Integer> {
	Optional<UspdDev> getByIdUspdDev(Integer id);

}
