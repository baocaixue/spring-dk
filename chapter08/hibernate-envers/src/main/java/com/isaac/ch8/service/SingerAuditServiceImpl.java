package com.isaac.ch8.service;

import com.google.common.collect.Lists;
import com.isaac.ch8.entities.SingerAudit;
import com.isaac.ch8.repos.SingerAuditRepository;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
    @Autowired
    private SingerAuditRepository singerAuditRepository;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    public SingerAudit findById(Long id) {
        return singerAuditRepository.findById(id).orElse(null);
    }

    @Override
    public SingerAudit save(SingerAudit singerAudit) {
        return singerAuditRepository.save(singerAudit);
    }

    @Override
    public SingerAudit findAuditByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(em);
        return auditReader.find(SingerAudit.class, id, revision);
    }
}
