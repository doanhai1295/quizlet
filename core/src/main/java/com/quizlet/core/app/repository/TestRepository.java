package com.quizlet.core.app.repository;



import com.quizlet.core.app.entity.TestEntity;
import com.quizlet.core.app.utils.QueryBuilder;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<TestEntity> findAll() throws Exception {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.from(TestEntity.class.getName());
        Query query = queryBuilder.build(entityManager);
        return query.getResultList();
    }
}
