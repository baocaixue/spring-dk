package com.isaac.ch10;

import lombok.Data;
import org.joda.time.DateTime;

import java.net.URL;
import java.text.SimpleDateFormat;

@Data
public class Singer {
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private URL personalSite;

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("{First name: %s, Last name: %s, Birthday: %s, Site: %s}",
                firstName, lastName, sdf.format(birthDate.toDate()), personalSite);
    }
}
