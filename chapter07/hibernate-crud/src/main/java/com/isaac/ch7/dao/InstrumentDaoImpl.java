package com.isaac.ch7.dao;

import com.isaac.ch7.entities.Instrument;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class InstrumentDaoImpl implements InstrumentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Instrument save(Instrument instrument) {
        sessionFactory.getCurrentSession().saveOrUpdate(instrument);
        return instrument;
    }
}
