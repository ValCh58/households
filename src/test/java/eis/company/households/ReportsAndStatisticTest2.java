/**
 * 
 */
package eis.company.households;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.restcontroller.ReportsAndStatistics;
import eis.company.households.service.ReportsService;

/**
 * @author Valeriy
 *
 */
@Disabled
@DisplayName(value = "Testing of  controller 2")
@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class ReportsAndStatisticTest2 {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private ReportsService reportsService;

    @Autowired
    private JacksonTester<AcntCountsDTO> jsonSuperHero;

	
	@Test
	void testtestGetTableAcntCounts() throws Exception {
		List<AcntCountsDTO> listDto = new ArrayList<>();
		listDto.add(new AcntCountsDTO(1, 12, "СХВ", "12345", LocalDate.now(), "Адрес 1"));
		listDto.add(new AcntCountsDTO(3, 32, "СГВ", "12347", LocalDate.now(), "Адрес 3"));
		
		when(reportsService.getAcntCounts(1)).thenReturn(listDto);
		MockHttpServletResponse response = 
				mvc.perform(get("/user/count_water/1").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		
		//System.out.println(response.getStatus());
		System.out.println(response.getContentAsString());
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		
	}

}
