package com.end_project.repository;

import com.end_project.domain.GroupItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GroupItemRepo extends CrudRepository<GroupItem, Long> {

    List<GroupItem> findAll();

}
