package com.pluralsight.springdataoverview;

import com.pluralsight.springdataoverview.entity.GuaranteeRequest;
import com.pluralsight.springdataoverview.entity.LoanRequest;
import com.pluralsight.springdataoverview.repository.GuaranteeRequestRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomImplTests {
    @Autowired
    private GuaranteeRequestRepository guaranteeRequestRepository;

    @Test
    public void shouldSaveCustomImpl() {
        final GuaranteeRequest toDelete = createGuaranteeRequest("London");
        final GuaranteeRequest toKeep = createGuaranteeRequest("Paris");

        guaranteeRequestRepository.save(toDelete);
        guaranteeRequestRepository.save(toKeep);

        guaranteeRequestRepository.deleteByOrigin(1l);

        Assertions.assertThat(guaranteeRequestRepository.findAll())
            .hasSize(1)
            .first()
            .isEqualToComparingFieldByField(toKeep);
    }

    private GuaranteeRequest createGuaranteeRequest(String origin) {
        final GuaranteeRequest guaranteeRequest = new GuaranteeRequest();
            guaranteeRequest.setSEPAM_CODE(origin);
            guaranteeRequest.setCUSTOMS_GUARANTEE_NUMBER("234");
        return guaranteeRequest;
    }
}
