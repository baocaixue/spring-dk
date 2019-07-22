package com.isaac.ch9.service;

import com.isaac.ch9.entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SingerServiceImpl implements SingerService{
    @Autowired
    private TransactionTemplate transactionTemplate;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Singer> findAll() {
        return null;
    }

    @Override
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer singer) {
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public long countAll() {
        return transactionTemplate.execute(transactionStatus -> em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult());
    }
}
