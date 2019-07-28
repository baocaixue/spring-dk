package com.isaac.ch10.obj;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
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

    @AssertTrue(message = "Error! Individual customer should have gender and last name defined")
    public boolean isCountrySinger() {
        boolean result = true;

        if (genre!= null &&
                (genre.equals(Genre.COUNTRY) && (gender == null || lastName == null))) {
            result = false;
        }

        return result;
    }
}
