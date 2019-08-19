package com.isaac.ch14

class Singer {
    def firstName
    def lastName
    def birthDate
    String toString() {
        "($firstName, $lastName, $birthDate)"
    }
}
