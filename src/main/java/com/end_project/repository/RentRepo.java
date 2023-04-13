package com.end_project.repository;

import com.end_project.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RentRepo extends CrudRepository<Rent, Long> {

    List<Rent> findAll();

}
