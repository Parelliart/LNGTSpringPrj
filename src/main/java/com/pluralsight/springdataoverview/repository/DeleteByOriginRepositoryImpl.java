package com.pluralsight.springdataoverview.repository;

import javax.persistence.EntityManager;

public class DeleteByOriginRepositoryImpl implements DeleteByOriginRepository {

    private final EntityManager entityManager;

    public DeleteByOriginRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void deleteByOrigin(Long id) {
        entityManager.createNativeQuery("DELETE from GuaranteeRequest WHERE id = ?")
            .setParameter(1, id)
            .executeUpdate();
    }
}
