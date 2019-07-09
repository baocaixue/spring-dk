package com.isaac.ch7.dao;

import com.isaac.ch7.entities.Singer;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("singerDao")
@Transactional
@SuppressWarnings("uncheck")
public class SingerDaoImpl implements SingerDao {
    private static Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Singer").list();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        return sessionFactory.getCurrentSession().createNamedQuery("Singer.findAllWithAlbum", Singer.class).list();
    }

    @Override
    public Singer findById(Long id) {
        return sessionFactory.getCurrentSession().createNamedQuery("Singer.findById", Singer.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
        logger.info("Singer saved with id : " + singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        sessionFactory.getCurrentSession().delete(singer);
    }
}
