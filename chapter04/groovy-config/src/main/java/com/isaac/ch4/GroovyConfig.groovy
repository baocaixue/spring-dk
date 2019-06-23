package com.isaac.ch4

import com.isaac.ch3.xml.Singer
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext

def ctx = new GenericApplicationContext()
def reader = new GroovyBeanDefinitionReader(ctx)

reader.beans {
    singer(Singer, name: 'Taylor Swift', age: 30)
}

ctx.refresh()

println(ctx.getBean("singer"))
