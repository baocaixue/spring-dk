package com.isaac.ch8.service;

import com.google.common.collect.Lists;
import com.isaac.ch8.entities.SingerAudit;
import com.isaac.ch8.repos.SingerAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SingerAudit> findById(Long id) {
        return singerAuditRepository.findById(id);
    }

    @Override
    public SingerAudit save(SingerAudit singer) {
        return singerAuditRepository.save(singer);
    }
}
