package com.isaac.ch14;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Singer {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String ageCategory;

    @Override
    public String toString() {
        return "Singer - Id: " + id + ", First name: " + firstName
                + ", Last name: " + lastName + ", Birthday: " + birthDate
                + ", Age category: " + ageCategory;
    }

}
