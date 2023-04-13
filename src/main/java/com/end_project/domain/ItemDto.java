package com.end_project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long groupItemId;
}
