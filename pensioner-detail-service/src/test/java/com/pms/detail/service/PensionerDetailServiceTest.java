package com.pms.detail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.pms.detail.exception.AadharNumberNotFound;
import com.pms.detail.model.Bank;
import com.pms.detail.model.PensionerDetail;
import com.pms.detail.util.CsvToListConverter;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PensionerDetailServiceTest {

	@InjectMocks
	private PensionerDetailServiceImpl pensionerDetailServiceImpl;

	@Mock
	private CsvToListConverter csvUtils;


	@Test
    void testGetPensionDetailByAadharCard() throws AadharNumberNotFound, NumberFormatException, IOException   {
		Bank bank = new Bank("SBI", "9029486523", "private");
		PensionerDetail pensionerDetail = new PensionerDetail(420559429029l, "Nabeel", LocalDate.of(1999, 12, 03),
				"BSDPS1495K", 29000, 1200, "self", bank);

		List<PensionerDetail> list = new ArrayList<PensionerDetail>();
		list.add(pensionerDetail);

		Mockito.when(csvUtils.processTxt()).thenReturn(list);
		assertEquals(pensionerDetailServiceImpl.getPensionerDetailByAadharCard(420559429029l), pensionerDetail);
	}

}