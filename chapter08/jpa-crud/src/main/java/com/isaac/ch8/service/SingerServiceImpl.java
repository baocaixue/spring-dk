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

    @PersistenceContext(unitName = "entityManagerFactory")
    //标准用于实体管理器注入的JPA注解，因为持久化上下文本身由EntityManager管理，多个持久化单元，用unitName属性指定。持久化单元通常表示独立的后端DataSource
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
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

    @Override
    public void delete(Singer singer) {

    }

    @Override
    public List<Singer> findAllByNativeQuery() {
        return null;
    }
}
