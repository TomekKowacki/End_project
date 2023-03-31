package com.end_project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {

    private Long id;
    private LocalDateTime dateOfRent;
    private LocalDateTime dateOfReturn;
    private String siteAddres;
    private List<Item> items;
    private Long userId;
}
