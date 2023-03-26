package com.end_project.repository;

import com.end_project.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends CrudRepository<Item, Long> {

    @Override
    List<Item> findAll();
}
