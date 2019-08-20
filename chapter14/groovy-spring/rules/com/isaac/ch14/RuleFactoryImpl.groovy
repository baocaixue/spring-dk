package com.isaac.ch14

import org.springframework.stereotype.Component

import java.time.LocalDate
import java.time.Period

/**
 * 为了允许动态刷新规则，需要将类放入外部文件夹，以便进行修改
 */
@Component
class RuleFactoryImpl implements RuleFactory{
    Closure age = {birthDate -> Period.between(birthDate, LocalDate.now()).getYears()}

    @Override
    Rule getAgeCategoryRule() {
        Rule rule = new Rule()
        rule.singlehit = true
        rule.conditions = [
                {object, param -> age(object.birthDate) >= param},
                {object, param -> age(object.birthDate) <= param}
        ]
        rule.actions = [{object,param -> object.ageCategory = param}]
        rule.parameters = [
                [0, 10, 'Kid'],
                [11, 20, 'Youth'],
                [21, 40, 'Adult'],
                [41, 60, 'Matured'],
                [61, 80, 'Middle-aged'],
                [81, 120, 'Old']
        ]
        return rule
    }
}
