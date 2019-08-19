package com.isaac.ch14

import java.time.LocalDate;

def swift = new Singer(firstName: 'Taylor', lastName: 'Swift', birthDate: LocalDate.of(1989, 12, 13))
def ed = new Singer(firstName: 'Ed', lastName: 'Sheeran', birthDate: LocalDate.of(1991, 2, 17))

println swift
println ed

println swift.firstName + 39

//Simplified Syntax
def list = ['Hello', 'World', '!']
println list

assert list.size() == 3
assert list.class == ArrayList.class
assert list.sort({it.size()}) == ['!', 'Hello', 'World']
println list
assert list[0..1] == ['!', 'Hello']

//Closure
def map = ['a': 10, 'b': 11]
def square = {key, value -> map[key] = value * value}
map.each square
println map
