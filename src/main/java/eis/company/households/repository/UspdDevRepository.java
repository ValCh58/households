/**
 * 
 */
package eis.company.households.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eis.company.households.model.UspdDev;

/**
 * @author Admin
 * @param <T>
 *
 */
@Repository
public interface UspdDevRepository extends JpaRepository<UspdDev, Integer> {
	Optional<UspdDev> getByIdUspdDev(Integer id);

}
