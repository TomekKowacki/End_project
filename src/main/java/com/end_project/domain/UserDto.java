package com.end_project.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String lastName;
    private int pesel;
    private int phone;
    private boolean isBlocked;
    private List<Rent> rentsList;
}
