package com.end_project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
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
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "RENT_DATE")
    private LocalDateTime dateOfRent;

    @Column(name = "RETURN_DATE")
    private LocalDateTime dateOfReturn;

    @Column(name = "SITE_ADDRES")
    private String siteAddres;

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
