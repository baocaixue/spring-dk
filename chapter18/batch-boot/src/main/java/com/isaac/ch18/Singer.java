package com.isaac.ch18;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Singer {
    private String firstName;
    private String lastName;
    private String song;
}
