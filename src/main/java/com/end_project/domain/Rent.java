package com.end_project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "DATE_OF_RENT")
    private LocalDateTime dateOfRent;

    @Column(name = "DATE_OF_RETURN")
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


    public Rent(LocalDateTime dateOfRent, LocalDateTime dateOfReturn, User user, Item item) {
        this.dateOfRent = dateOfRent;
        this.dateOfReturn = dateOfReturn;
        this.user = user;
        this.item = (List<Item>) item;
    }


}
