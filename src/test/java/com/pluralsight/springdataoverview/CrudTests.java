package com.pluralsight.springdataoverview;

import static org.assertj.core.api.Assertions.assertThat;

import com.pluralsight.springdataoverview.entity.GuaranteeRequest;
import com.pluralsight.springdataoverview.repository.GuaranteeRequestRepository;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CrudTests {

	@Autowired
	private GuaranteeRequestRepository guaranteeRequestRepository;

	@Test
//	@Sql({"/schema_test.sql", "/data_test.sql"})
	public void shouldPerformCRUDOperations() {

		final GuaranteeRequest guaranteeRequest =  new GuaranteeRequest();
		guaranteeRequest.setSEPAM_CODE("0111199637066675");
		guaranteeRequest.setCUSTOMS_GUARANTEE_NUMBER("0111");
		guaranteeRequest.setSTAKEHOLDER_DATE(LocalDateTime.parse("2022-05-13T12:12:00"));

		guaranteeRequestRepository.save(guaranteeRequest);

		assertThat(guaranteeRequestRepository.findAll())
			.first()
			.isEqualToComparingFieldByField(guaranteeRequest);

		//guaranteeRequestRepository.deleteById(guaranteeRequest.getId());

		//if(assertThat(guaranteeRequestRepository.count()).isZero());
	}

}
