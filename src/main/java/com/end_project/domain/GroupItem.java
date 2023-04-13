package com.end_project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ITEMS_GROUP")
public class GroupItem {

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "groupItem",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();
}
