package eis.company.households;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.restcontroller.ReportsAndStatistics;
import eis.company.households.service.ReportsService;
import eis.company.households.utility.ReportUtils;


@DisplayName(value = "Controller \"/user/count_water/{id}\"")
@AutoConfigureMockMvc
@SpringBootTest
class ReportsAndStatisticTest3 {
	List<AcntCountsDTO> listDto = new ArrayList<>();
	
	@MockBean
	ReportsService reportsService;
	@MockBean
	ReportUtils reportUtils;
	
	@Autowired
	MockMvc mockMvc;

    @BeforeEach
    void setup() {
         this.mockMvc = MockMvcBuilders.standaloneSetup(new ReportsAndStatistics(reportsService, reportUtils)).build();
    }
    

	@Test
	void test() throws Exception {
		listDto.add(new AcntCountsDTO(1, 12, "СХВ", "12345", LocalDate.now(), "Адрес 1"));
		
		
		Mockito.when(reportsService.getAcntCounts(anyInt())).thenReturn(listDto);
				
		 this.mockMvc
	    .perform(MockMvcRequestBuilders.get("/user/count_water/{id}", anyInt())
	    .accept(MediaType.APPLICATION_JSON))
	    .andDo(print())
	    .andExpect(MockMvcResultMatchers.status().isOk());
			
	}

}
