package com.isaac.ch8.service;

import com.isaac.ch8.view.SingerSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 使用构造函数表达式查询自定义结果类型
 */
@Service
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<SingerSummary> findAll() {
        return entityManager.createQuery("select new com.isaac.ch8.view.SingerSummary(s.firstName,s.lastName,a.title) from Singer s " +
                " left join s.albums a where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id=s.id)", SingerSummary.class).getResultList();
    }
}
