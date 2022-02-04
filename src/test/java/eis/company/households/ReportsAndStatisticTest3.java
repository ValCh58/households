package eis.company.households;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.restcontroller.ReportsAndStatistics;
import eis.company.households.service.ReportsService;


@DisplayName(value = "Testing of  controller 3")
@ExtendWith(MockitoExtension.class)
class ReportsAndStatisticTest3 {
	List<AcntCountsDTO> listDto = new ArrayList<>();
	
	@Mock
	ReportsService reportsService;
	
	
	MockMvc mockMvc;

    @BeforeEach
    void setup() {
         this.mockMvc = MockMvcBuilders.standaloneSetup(new ReportsAndStatistics())
        		       .build();
    }

	@Test
	void test() throws Exception {
		listDto.add(new AcntCountsDTO(1, 12, "СХВ", "12345", LocalDate.now(), "Адрес 1"));
		listDto.add(new AcntCountsDTO(3, 32, "СГВ", "12347", LocalDate.now(), "Адрес 3"));
		
		when(reportsService.getAcntCounts(anyInt()))
		.thenThrow(new RuntimeException())
		.thenReturn(listDto);
		
		MvcResult mvcResult  = this.mockMvc
	    .perform(MockMvcRequestBuilders.get("/user/count_water/{id}", 1).accept(MediaType.APPLICATION_JSON))
	    .andDo(print())
	    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		

		
		
		System.out.println("STATUS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   "+mvcResult);
	}

}
