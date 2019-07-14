package com.isaac.ch8.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

/**
 * 查询非类型化结果：向数据库提交查询并随意操作结果，而不是将它们存储在映射的实体类中。
 */
@Service
@Repository
@Transactional
public class SingerSummaryUntypeImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Iterator<Object[]> findAllSingerSummary() {
        List resultList = entityManager.createQuery("select s.firstName, a.title from Singer s left join s.albums a where a.releaseDate=(select max(a2.releaseDate) from Album a2 where a2.singer.id = s.id)").getResultList();
        return resultList.iterator();
    }
}
