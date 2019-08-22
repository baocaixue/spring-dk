package com.isaac.ch15;

import org.hibernate.SessionFactory;
import org.hibernate.stat.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 使用Hibernate的org.hibernate.stat.Statistics实现作为核心组件来声明statisticsBean方法
 * https://github.com/manuelbernhardt/hibernate-core/blob/master/hibernate-core/src/main/java/org/hibernate/jmx/StatisticsService.java
 */
public class CustomStatistics implements InitializingBean{
    @Autowired
    private SessionFactory sessionFactory;
    private Statistics stats;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.stats = sessionFactory.getStatistics();
    }

    public EntityStatistics getEntityStatistics(String entityName) {
        return stats.getEntityStatistics(entityName);
    }

    public CollectionStatistics getCollectionStatistics(String role) {
        return stats.getCollectionStatistics(role);
    }

    public SecondLevelCacheStatistics getSecondLevelCacheStatistics(String regionName) {
        return stats.getSecondLevelCacheStatistics(regionName);
    }

    public QueryStatistics getQueryStatistics(String hql) {
        return stats.getQueryStatistics(hql);
    }

    public long getEntityDeleteCount() {
        return stats.getEntityDeleteCount();
    }

    public long getEntityInsertCount() {
        return stats.getEntityInsertCount();
    }

    public long getEntityLoadCount() {
        return stats.getEntityLoadCount();
    }

    public long getEntityFetchCount() {
        return stats.getEntityFetchCount();
    }

    public long getEntityUpdateCount() {
        return stats.getEntityUpdateCount();
    }

    public long getQueryExecutionCount() {
        return stats.getQueryExecutionCount();
    }

    public long getQueryCacheHitCount() {
        return stats.getQueryCacheHitCount();
    }

    public long getQueryExecutionMaxTime() {
        return stats.getQueryExecutionMaxTime();
    }

    public long getQueryCacheMissCount() {
        return stats.getQueryCacheMissCount();
    }

    public long getQueryCachePutCount() {
        return stats.getQueryCachePutCount();
    }

    public long getFlushCount() {
        return stats.getFlushCount();
    }

    public long getConnectCount() {
        return stats.getConnectCount();
    }

    public long getSecondLevelCacheHitCount() {
        return stats.getSecondLevelCacheHitCount();
    }

    public long getSecondLevelCacheMissCount() {
        return stats.getSecondLevelCacheMissCount();
    }

    public long getSecondLevelCachePutCount() {
        return stats.getSecondLevelCachePutCount();
    }

    public long getSessionCloseCount() {
        return stats.getSessionCloseCount();
    }

    public long getSessionOpenCount() {
        return stats.getSessionOpenCount();
    }

    public long getCollectionLoadCount() {
        return stats.getCollectionLoadCount();
    }

    public long getCollectionFetchCount() {
        return stats.getCollectionFetchCount();
    }

    public long getCollectionUpdateCount() {
        return stats.getCollectionUpdateCount();
    }

    public long getCollectionRemoveCount() {
        return stats.getCollectionRemoveCount();
    }

    public long getCollectionRecreateCount() {
        return stats.getCollectionRecreateCount();
    }

    public long getStartTime() {
        return stats.getStartTime();
    }

    public boolean isStatisticsEnabled() {
        return stats.isStatisticsEnabled();
    }

    public String[] getEntityNames() {
        return stats.getEntityNames();
    }

    public String[] getQueries() {
        return stats.getQueries();
    }

    public long getSuccessfulTransactionCount() {
        return stats.getSuccessfulTransactionCount();
    }
    public long getTransactionCount() {
        return stats.getTransactionCount();
    }

    public String getQueryExecutionMaxTimeQueryString() {
        return stats.getQueryExecutionMaxTimeQueryString();
    }
}
