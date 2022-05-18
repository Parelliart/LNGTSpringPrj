package com.pluralsight.springdataoverview.repository;

import java.util.Optional;

import com.pluralsight.springdataoverview.entity.GuaranteeRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuaranteeRequestRepository extends PagingAndSortingRepository<GuaranteeRequest, Long>, DeleteByOriginRepository {

    Optional<GuaranteeRequest> findById(Long id);

//    List<GuaranteeRequest> findByOriginAndDestination(String london, String destination);
//
//    List<GuaranteeRequest> findByOriginIn(String ... origins);
//
//    List<GuaranteeRequest> findByOriginIgnoreCase(String origin);
//
//    Page<GuaranteeRequest> findByOrigin(String origin, Pageable pageRequest);
}
