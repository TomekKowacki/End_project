package com.end_project.repository;

import com.end_project.domain.GroupItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupItemRepo extends CrudRepository<GroupItem, Long> {

    @Override
    List<GroupItem> findAll();

}
