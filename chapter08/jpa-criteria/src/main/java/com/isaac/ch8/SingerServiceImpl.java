package com.isaac.ch8;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service
@Repository
@Transactional
public class SingerServiceImpl implements  SingerService {
    private Log log = LogFactory.getLog(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Singer> findAll() {
        return null;
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

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery = cb.createQuery(Singer.class);
        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);

        criteriaQuery.select(singerRoot).distinct(true);
        Predicate conjunction = cb.conjunction();
        if(firstName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.firstName), firstName);
            conjunction = cb.and(conjunction, p);
        }
        if(lastName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.lastName), lastName);
            conjunction = cb.and(conjunction, p);
        }
        criteriaQuery.where(conjunction);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
