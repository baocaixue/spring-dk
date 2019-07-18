package com.isaac.ch8.service;

import com.isaac.ch8.entities.SingerAudit;

import java.util.List;

public interface SingerAuditService {
    List<SingerAudit> findAll();
    SingerAudit findById(Long id);
    SingerAudit save(SingerAudit singerAudit);

    /**
     * 通过版本号检索SingerAudit历史记录
     * @param id 实体ID
     * @param revision 版本号
     * @return
     */
    SingerAudit findAuditByRevision(Long id, int revision);
}
