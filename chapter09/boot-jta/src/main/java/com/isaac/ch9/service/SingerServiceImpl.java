package com.isaac.ch9.service;

import com.isaac.ch9.entities.Singer;
import com.isaac.ch9.ex.AsyncXAResourcesException;
import com.isaac.ch9.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerRepository singerRepository;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public Singer save(Singer singer) {
        jmsTemplate.convertAndSend("singers", "Just saved:" + singer);
        if (singer == null)
            throw new AsyncXAResourcesException("Simulation of something going wrong.");
        singerRepository.save(singer);
        return singer;
    }

    @Override
    public long count() {
        return singerRepository.count();
    }
}
