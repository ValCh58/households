package eis.company.households;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import eis.company.households.queres.QueryAcntCountsDto;

@ExtendWith(MockitoExtension.class)
class ReportsServiceTest {
	
	@Mock private QueryAcntCountsDto qAcntCountDtoMock;

	@Test
	void testGetAcntCounts() {
		when(qAcntCountDtoMock.getAcntCountsDTO(1)).thenReturn(null);
		
		assertNotNull("Not Null");
		
	}

}
