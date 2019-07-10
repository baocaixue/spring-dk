package com.isaac.ch7.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Hibernate root entity
 */
@MappedSuperclass
public class AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)//在使用“UPDATE”脚本插入数据时，是否需要更新该字段的值
    protected Long id;

    @Version
    @Column(name = "VERSION")
    private int version;

    /**
     * 持久性框架使用，不应由程序代码使用
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 持久性框架设置，不应由程序代码设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        AbstractEntity that = (AbstractEntity) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
