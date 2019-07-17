package com.isaac.ch8.service;

import com.isaac.ch8.entities.SingerAudit;

import java.util.List;
import java.util.Optional;

public interface SingerAuditService {
    List<SingerAudit> findAll();
    Optional<SingerAudit> findById(Long id);
    SingerAudit save(SingerAudit singer);
}
