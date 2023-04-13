package com.end_project.domain;

import lombok.*;
import org.springframework.stereotype.Repository;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "PESEL")
    private int pesel;

    @Column(name = "PHONE")
    private int phone;

    @Column(name = "BLOCK")
    private boolean isBlocked;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Rent> rents = new ArrayList<>();
}
