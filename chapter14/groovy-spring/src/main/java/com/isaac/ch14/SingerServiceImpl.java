package com.isaac.ch14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("singerService")
public class SingerServiceImpl implements SingerService {
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private RuleFactory ruleFactory;
    @Autowired
    private RuleEngine ruleEngine;
    
    @Override
    public void applyRule(Singer singer) {
        Rule ageCategoryRule = ruleFactory.getAgeCategoryRule();
        ruleEngine.run(ageCategoryRule, singer);
    }
}
