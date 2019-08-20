package com.isaac.ch14;

/**
 * 规则执行引擎
 */
public interface RuleEngine {
    void run(Rule rule, Object object);
}
