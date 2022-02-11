package eis.company.households;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import eis.company.households.dto.AcntCountsDTO;
import eis.company.households.queres.QueryAcntCountsDto;
import eis.company.households.service.ReportsService;


@ExtendWith(MockitoExtension.class)
class ReportsServiceTest {
	
	@Mock 
	private QueryAcntCountsDto queryAcntCountsDtoMock;
	
	@InjectMocks
	private ReportsService qAcntCountDto;

	@Test
	void testGetAcntCounts() {
		List<AcntCountsDTO> listDto = new ArrayList<>();
		listDto.add(new AcntCountsDTO(1, 12, "СХВ", "12345", LocalDate.now(), "Адрес 1"));
				
		when(queryAcntCountsDtoMock.getAcntCountsDTO(any())).thenReturn(listDto);
		List<AcntCountsDTO> dto = qAcntCountDto.getAcntCounts(any());
		assertNotNull(dto);
		
		assertEquals(1, dto.get(0).getIdCounts());
		assertEquals(12, dto.get(0).getIdPersonAcnt());
		assertEquals("СХВ", dto.get(0).getNameCount());
		assertEquals("12345", dto.get(0).getSerialNum());
		assertNotNull(dto.get(0).getDateExpire());
		assertEquals("Адрес 1", dto.get(0).getAddress());
		
	}

}
