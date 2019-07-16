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
        //调用EntityManager.getCriteriaBuilder()来检索CriteriaBuilder的一个实例
        CriteriaBuilder cb = em.getCriteriaBuilder();
        //调用CriteriaBuilder.createQuery()创建类型化查询，并传入Singer作为结果类型
        CriteriaQuery<Singer> criteriaQuery = cb.createQuery(Singer.class);
        //调用CriteriaQuery.from()方法，并传入实体类。返回结果是与指定实体相对应的查询根对象（Root<Singer>接口）。查询根对象构成查询中路径表达式的基础
        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        //调用两次Root.fetch()方法，立即提取与专辑和乐器相关的关联。JoinType.LEFT参数指定一个外部连接。
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);

        //调用CriteriaQuery.select()方法，并传入根查询对象作为查询结果。使用distinct(true)方法意味着消除重复记录
        criteriaQuery.select(singerRoot).distinct(true);
        //Predicate实例是通过调用CriteriaBuilder.conjunction()方法获得的，这意味着会产生一个或多个限制条件的组合。Predicate实例可以是简单谓词或复合谓词，谓词用于表示由表达式定义的选择条件
        Predicate conjunction = cb.conjunction();
        //检查名字和姓氏参数。对于每个非空参数，都会使用CriteriaBuilder.and()方法构造一个新的Predicate实例。方法equal()用来指定相等限制，在该方法中调用Root.get()并传入限制适用的实体类元模型的相应属性。
        //然后将构造的谓词通过CriteriaBuilder.and()方法与现有谓词（由变量条件存储）“合并”
        if(firstName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.firstName), firstName);
            conjunction = cb.and(conjunction, p);
        }
        if(lastName != null) {
            Predicate p = cb.equal(singerRoot.get(Singer_.lastName), lastName);
            conjunction = cb.and(conjunction, p);
        }
        //Predicate实例是使用所有条件和限制构造的，并通过CriteriaQuery.where()方法作为where子句传递给查询
        criteriaQuery.where(conjunction);
        //最后CriteriaQuery被传递给EntityManager。然后，EntityManager根据传入的CriteriaQuery值构造查询，执行查询并返回结果
        return em.createQuery(criteriaQuery).getResultList();
    }
}
