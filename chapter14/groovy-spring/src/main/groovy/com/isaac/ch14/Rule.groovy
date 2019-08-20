package com.isaac.ch14

class Rule {
    /**规则是否应该在找到匹配条件时立即结束*/
    def singlehit = true
    /**定义了规则引擎应该检查处理的域对象的各种条件*/
    def conditions = new ArrayList()
    /**在条件匹配时要采取的动作*/
    def actions = new ArrayList()
    /**定义了规则的行为，即不同条件下行为的结果*/
    def parameters = new ArrayList()
}
