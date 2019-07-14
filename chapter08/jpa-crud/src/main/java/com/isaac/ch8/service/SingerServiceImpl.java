package com.isaac.ch8.service;

import com.isaac.ch8.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);
    final static String ALL_SINGER_NATIVE_QUERY ="select id, first_name, last_name, birth_date, version from singer";

    @PersistenceContext(unitName = "entityManagerFactory")
    //标准用于实体管理器注入的JPA注解，因为持久化上下文本身由EntityManager管理，多个持久化单元，用unitName属性指定。持久化单元通常表示独立的后端DataSource
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbum() {
        return em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }

    @Override
    public Singer findById(Long id) {
        return em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if(singer.getId() == null) {
            logger.info("insert singer ");
            em.persist(singer);
        } else {
            logger.info("update exist singer");
            em.merge(singer);
        }
        logger.info("Singer saved with id: " + singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        //Removing a detached instance if not exit this code
        Singer merge = em.merge(singer);
        em.remove(merge);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllByNativeQuery() {
        //return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, Singer.class).getResultList();
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
    }
}
