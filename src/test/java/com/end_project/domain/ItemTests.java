package com.end_project.domain;

import com.end_project.repository.ItemRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemTests {

    private ItemRepo itemRepository;

    @Test
    void testFindItemById() {

        //Given
        Item mlot = new Item();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        Item pila = new Item();
        pila.setName("Piła");
        pila.setPrice(new BigDecimal("60.49"));
        pila.setDescription("Do drewna");

        Item koparka = new Item();
        koparka.setName("Koparka");
        koparka.setPrice(new BigDecimal("600.50"));
        koparka.setDescription("3,5T");

        itemRepository.save(mlot);
        itemRepository.save(pila);
        itemRepository.save(koparka);

        //When
        Long mlotId = mlot.getId();
        Long pilaId = pila.getId();
        Long koparkaId = koparka.getId();

        Optional<Item> Item1 = itemRepository.findById(mlotId);
        Optional<Item> Item2 = itemRepository.findById(pilaId);
        Optional<Item> Item3 = itemRepository.findById(koparkaId);

        //Then
        assertEquals(mlotId, Item1.get().getId());
        assertEquals(pilaId, Item2.get().getId());
        assertEquals(koparkaId, Item3.get().getId());

        //CleanUp
        itemRepository.deleteAll();
    }

    @Test
    void testDeleteItemById() {

        //Given
        Item mlot = new Item();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        Item pila = new Item();
        pila.setName("Piła");
        pila.setPrice(new BigDecimal("60.49"));
        pila.setDescription("Do drewna");

        Item koparka = new Item();
        koparka.setName("Koparka");
        koparka.setPrice(new BigDecimal("600.50"));
        koparka.setDescription("3,5T");

        itemRepository.save(mlot);
        itemRepository.save(pila);
        itemRepository.save(koparka);

        //When
        itemRepository.deleteById(koparka.getId());
        itemRepository.deleteById(pila.getId());

        List<Item> items = (List<Item>) itemRepository.findAll();

        //Then
        assertEquals(2, items.size());
        assertEquals(koparka.getId(), items.get(0).getId());
        assertEquals(pila.getId(), items.get(1).getId());

        //CleanUp
        itemRepository.deleteAll();
    }
}
