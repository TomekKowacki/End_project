package com.end_project.service;

import com.end_project.domain.Item;
import com.end_project.exception.ItemNotFoundException;
import com.end_project.repository.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemDbService {

    private final ItemRepo repository;

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Item getItem(Long id) throws ItemNotFoundException {
        return repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public Item saveItem(Item item) {
        return repository.save(item);
    }

    public void deleteItem (Long id) throws ItemNotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ItemNotFoundException();
        }
    }
}
