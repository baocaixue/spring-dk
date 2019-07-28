package com.isaac.ch10.obj;

import java.util.Optional;

public enum Gender {
    MALE("M"), FEMALE("F");
    private String code;

    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return Optional.of(this.code).map(c -> "M".equalsIgnoreCase(c) ? "男" : "女").orElse("");
    }
}
