package com.pluralsight.springdataoverview;

import static org.assertj.core.api.Assertions.assertThat;

import com.pluralsight.springdataoverview.entity.GuaranteeRequest;
import com.pluralsight.springdataoverview.repository.GuaranteeRequestRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DerivedQueryTests {
    @Autowired
    private GuaranteeRequestRepository guaranteeRequestRepository;

    @Before
    public void setUp() {
        guaranteeRequestRepository.deleteAll();
    }

    @Test
    public void shouldFindFlightsFromLondon() {
        final GuaranteeRequest guaranteeRequest1 = createGuaranteeRequest("London");
        final GuaranteeRequest guaranteeRequest2 = createGuaranteeRequest("London");
        final GuaranteeRequest guaranteeRequest3 = createGuaranteeRequest("New York");

        guaranteeRequestRepository.save(guaranteeRequest1);
        guaranteeRequestRepository.save(guaranteeRequest2);
        guaranteeRequestRepository.save(guaranteeRequest3);

        //List<GuaranteeRequest> flightsToLondon = guaranteeRequestRepository.findById(260054743662l);

//        assertThat(flightsToLondon).hasSize(2);
//        assertThat(flightsToLondon.get(0)).isEqualToComparingFieldByField(guaranteeRequest1);
//        assertThat(flightsToLondon.get(1)).isEqualToComparingFieldByField(guaranteeRequest2);
    }

    @Test
    public void shouldFindFlightsFromLondonToParis() {
        final GuaranteeRequest flight1 = createGuaranteeRequest("London", "Paris");
        final GuaranteeRequest flight2 = createGuaranteeRequest("London", "New York");
        final GuaranteeRequest flight3 = createGuaranteeRequest("Madrid", "Paris");

        guaranteeRequestRepository.save(flight1);
        guaranteeRequestRepository.save(flight2);
        guaranteeRequestRepository.save(flight3);

//        final List<GuaranteeRequest> londonToParis = guaranteeRequestRepository
//            .findByOriginAndDestination("London", "Paris");
//
//        assertThat(londonToParis)
//            .hasSize(1)
//            .first()
//            .isEqualToComparingFieldByField(flight1);
    }

    @Test
    public void shouldFindFlightsFromLondonOrMadrid() {
        final GuaranteeRequest flight1 = createGuaranteeRequest("London", "New York");
        final GuaranteeRequest flight2 = createGuaranteeRequest("Tokyo", "New York");
        final GuaranteeRequest flight3 = createGuaranteeRequest("Madrid", "New York");

        guaranteeRequestRepository.save(flight1);
        guaranteeRequestRepository.save(flight2);
        guaranteeRequestRepository.save(flight3);

//        final List<GuaranteeRequest> londonOrMadrid = guaranteeRequestRepository
//            .findByOriginIn("London", "Madrid");
//
//        assertThat(londonOrMadrid).hasSize(2);
//        assertThat(londonOrMadrid.get(0)).isEqualToComparingFieldByField(flight1);
//        assertThat(londonOrMadrid.get(1)).isEqualToComparingFieldByField(flight3);
    }

    @Test

    public void shouldFindFlightsFromLondonIgnoringCase() {
        final GuaranteeRequest flight = createGuaranteeRequest("LONDON");

        guaranteeRequestRepository.save(flight);

//        final List<GuaranteeRequest> flightsToLondon = guaranteeRequestRepository.findByOriginIgnoreCase("London");
//
//        assertThat(flightsToLondon)
//            .hasSize(1)
//            .first()
//            .isEqualToComparingFieldByField(flight);
    }

    private GuaranteeRequest createGuaranteeRequest(String origin, String destination) {
        final GuaranteeRequest guaranteeRequest = new GuaranteeRequest();
         return guaranteeRequest;
    }

    private GuaranteeRequest createGuaranteeRequest(String origin) {
        return createGuaranteeRequest(origin, "Madrid");
    }
}
