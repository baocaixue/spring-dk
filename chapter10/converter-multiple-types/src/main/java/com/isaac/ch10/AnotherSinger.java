package com.isaac.ch10;

import lombok.Data;

import java.net.URL;
import java.time.LocalDate;

@Data
public class AnotherSinger {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private URL personalSite;

    public String toString() {
        return String.format("{First name: %s, Last name: %s, Birthday: %s, Site: %s}",
                firstName, lastName, birthDate, personalSite);
    }
}
