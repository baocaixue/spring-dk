package com.isaac.ch10.obj;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Singer {
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;

    private String lastName;

    @NotNull
    private Genre genre;

    private Gender gender;
}
