package com.end_project.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {

    private Long id;
    private LocalDate dateOfRent;
    private LocalDate dateOfReturn;
    private String siteAddres;
    private List<Item> items;
    private Long userId;
}
