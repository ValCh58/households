package eis.company.households;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import eis.company.households.model.ManagCompany;
import eis.company.households.model.TypeObject;
import eis.company.households.repository.ManagCompanyRepository;
import eis.company.households.repository.TypeObjectRepository;


@SpringBootTest
@DisplayName(value = "Testing of ManagCompany")
class test_Repo_ManagCompany {
	
	@Autowired private ManagCompanyRepository managCompanyRepository; 
	@Autowired private TypeObjectRepository typeObjectRepository;

	@Test
	void addRowInto_ManagCompany() {
		ManagCompany mc = new ManagCompany();
		mc.setAddress1("Adress1");
		mc.setAddress2("Adress2");
		mc.setNameCompany("MyCompany");
		mc.setPhone("22222");
		mc.setStreet(null);
		mc.setComServer(null);
		mc.setTypeObject(typeObjectRepository.findById(1).get());
		mc = managCompanyRepository.save(mc);
		assertEquals("Adress1", mc.getAddress1());
		assertEquals("Adress2", mc.getAddress2());
		assertEquals("MyCompany", mc.getNameCompany());
		assertEquals("22222", mc.getPhone());
		managCompanyRepository.delete(mc);
	}

}
