package eis.company.households;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import eis.company.households.model.ManagCompany;
import eis.company.households.repository.ManagCompanyRepository;


@SpringBootTest
class test_Repo_ManagCompany {
	
	@Autowired private ManagCompanyRepository managCompanyRepository; 

	@Test
	void test_get_NameCompany() {
		Optional<ManagCompany> mc = managCompanyRepository.findById(1);
		assertEquals("УК \"Наш дом 39\"", mc.get().getNameCompany().trim());
	}
	
	@Test
	void addRowInto_ManagCompany() {
		ManagCompany mc = new ManagCompany();
		mc.setAddress1("Adress1");
		mc.setAddress2("Adress2");
		mc.setNameCompany("MyCompany");
		mc.setPhone("22222");
		mc.setStreet(null);
		
		
	}

}
