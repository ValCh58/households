package eis.company.households;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import eis.company.households.model.ManagCompany;
import eis.company.households.repository.ManagCompanyRepository;

//@EnableConfigurationProperties
//@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class testJPA_ManagCompany {
	
	//@Autowired private EntityManager entityManager;
	@Autowired private ManagCompanyRepository managCompanyRepository; 

	@Test
	void test() {
		Optional<ManagCompany> mc = managCompanyRepository.findById(1);
		//assertEquals("УК \"Наш дом 39\"", mc.getNameCompany().trim());
		//System.out.println("TEST!!!!!!!!!!!!!!!!!!!!" + mc.getNameCompany().trim());
	}

}
