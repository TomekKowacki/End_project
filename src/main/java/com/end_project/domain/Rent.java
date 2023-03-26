package com.end_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RENT")
public class Rent {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "RENT_DATE")
    private LocalDate dateOfRent;

    @Column(name = "RETURN_DATE")
    private LocalDate dateOfReturn;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "rent",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Item> item = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


}
