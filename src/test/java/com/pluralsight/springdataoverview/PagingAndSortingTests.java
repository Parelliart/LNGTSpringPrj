package com.pluralsight.springdataoverview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

import com.pluralsight.springdataoverview.entity.GuaranteeRequest;
import com.pluralsight.springdataoverview.repository.GuaranteeRequestRepository;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PagingAndSortingTests {

    @Autowired
    private GuaranteeRequestRepository guaranteeRequestRepository;

    @Before
    public void setUp() {
        guaranteeRequestRepository.deleteAll();
    }

    @Test
    public void shouldSortFlightsByDestination() {
        final GuaranteeRequest madrid = createGuaranteeRequest("Madrid");
        final GuaranteeRequest london = createGuaranteeRequest("London");
        final GuaranteeRequest  paris = createGuaranteeRequest("Paris");

        guaranteeRequestRepository.save(madrid);
        guaranteeRequestRepository.save(london);
        guaranteeRequestRepository.save(paris);

        final Iterable<GuaranteeRequest> flights = guaranteeRequestRepository.findAll(Sort.by("destination"));

        assertThat(flights).hasSize(3);

        final Iterator<GuaranteeRequest> iterator = flights.iterator();

        assertThat(iterator.next().getSEPAM_CODE()).isEqualTo("London");
        assertThat(iterator.next().getSTAKEHOLDER_NUMBER()).isEqualTo("Madrid");
        assertThat(iterator.next().getSEPAM_CODE()).isEqualTo("Paris");
    }

    @Test
    public void shouldSortFlightsByScheduledAndThenName() {
        final LocalDateTime now = LocalDateTime.now();
        final GuaranteeRequest guaranteeRequest1 = createGuaranteeRequest("Paris", now);
        final GuaranteeRequest guaranteeRequest2 = createGuaranteeRequest("Paris", now.plusHours(2));
        final GuaranteeRequest guaranteeRequest3 = createGuaranteeRequest("Paris", now.minusHours(1));
        final GuaranteeRequest london1 = createGuaranteeRequest("London", now.plusHours(1));
        final GuaranteeRequest london2 = createGuaranteeRequest("London", now);

        guaranteeRequestRepository.save(guaranteeRequest1);
        guaranteeRequestRepository.save(guaranteeRequest2);
        guaranteeRequestRepository.save(guaranteeRequest3);
        guaranteeRequestRepository.save(london1);
        guaranteeRequestRepository.save(london2);

        final Iterable<GuaranteeRequest> flights = guaranteeRequestRepository
            .findAll(Sort.by("destination", "scheduledAt"));

        assertThat(flights).hasSize(5);

        final Iterator<GuaranteeRequest> iterator = flights.iterator();

        assertThat(iterator.next()).isEqualToComparingFieldByField(london2);
        assertThat(iterator.next()).isEqualToComparingFieldByField(london1);
        assertThat(iterator.next()).isEqualToComparingFieldByField(guaranteeRequest3);
        assertThat(iterator.next()).isEqualToComparingFieldByField(guaranteeRequest1);
        assertThat(iterator.next()).isEqualToComparingFieldByField(guaranteeRequest2);
    }

    @Test
    public void shouldPageResults() {
        for (int i = 0; i < 50; i++) {
            guaranteeRequestRepository.save(createGuaranteeRequest(String.valueOf(i)));
        }

        final Page<GuaranteeRequest> page = guaranteeRequestRepository.findAll(PageRequest.of(2, 5));

        assertThat(page.getTotalElements()).isEqualTo(50);
        assertThat(page.getNumberOfElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(10);
        assertThat(page.getContent())
            .extracting(GuaranteeRequest::getSEPAM_CODE)
            .containsExactly("10", "11", "12", "13", "14");
    }

    @Test
    public void shouldPageAndSortResults() {
        for (int i = 0; i < 50; i++) {
            guaranteeRequestRepository.save(createGuaranteeRequest(String.valueOf(i)));
        }

        final Page<GuaranteeRequest> page = guaranteeRequestRepository
            .findAll(PageRequest.of(2, 5, Sort.by(DESC, "destination")));

        assertThat(page.getTotalElements()).isEqualTo(50);
        assertThat(page.getNumberOfElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(10);
        assertThat(page.getContent())
            .extracting(GuaranteeRequest::getSEPAM_CODE)
            .containsExactly("44", "43", "42", "41", "40");
    }

    @Test
    public void shouldPageAndSortADerivedQuery() {
        for (int i = 0; i < 10; i++) {
            final GuaranteeRequest guaranteeRequest = createGuaranteeRequest(String.valueOf(i));
            guaranteeRequest.setCUSTOMS_GUARANTEE_NUMBER("Paris");
            guaranteeRequestRepository.save(guaranteeRequest);
        }

        for (int i = 0; i < 10; i++) {
            final GuaranteeRequest guaranteeRequest = createGuaranteeRequest(String.valueOf(i));
            guaranteeRequest.setSEPAM_CODE("London");
            guaranteeRequestRepository.save(guaranteeRequest);
        }

/*        final List<GuaranteeRequest> page = guaranteeRequestRepository
            .findById(260054743662l*//*,
                PageRequest.of(0, 5, Sort.by(DESC, "destination"))*//*);

        assertThat(page.getTotalElements()).isEqualTo(10);
        assertThat(page.getNumberOfElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getContent())
            .extracting(GuaranteeRequest::getSEPAM_CODE)
            .containsExactly("9", "8", "7", "6", "5");*/
    }

    private GuaranteeRequest createGuaranteeRequest(String destination, LocalDateTime scheduledAt) {
        GuaranteeRequest guaranteeRequest = new GuaranteeRequest();

        return guaranteeRequest;
    }

    private GuaranteeRequest createGuaranteeRequest(String destination) {
        return createGuaranteeRequest(destination, LocalDateTime.parse("2011-12-13T12:12:00"));
    }
}
