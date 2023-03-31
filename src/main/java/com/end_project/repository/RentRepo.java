package com.end_project.repository;

import com.end_project.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepo extends CrudRepository<Rent, Long> {

    @Override
    List<Rent> findAll();

}
