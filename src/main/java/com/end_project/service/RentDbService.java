package com.end_project.service;

import com.end_project.domain.Rent;
import com.end_project.exception.RentNotFoundException;
import com.end_project.repository.RentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDbService {

    private final RentRepo repository;

    public List<Rent> getAllRents() {
        return repository.findAll();
    }

    public Rent getRent(Long id) throws RentNotFoundException {
        return repository.findById(id).orElseThrow(RentNotFoundException::new);
    }

    public Rent saveRent(Rent rent) {
        return repository.save(rent);
    }

    public void deleteRent(Long id) throws RentNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RentNotFoundException();
        }
    }
}
