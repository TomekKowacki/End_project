package com.end_project.domain;

import com.end_project.repository.ItemRepo;
import com.end_project.repository.RentRepo;
import com.end_project.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentTests {

    private RentRepo rentRepository;
    private UserRepo userRepository;
    private ItemRepo itemRepository;

    @Test
    void testFindRentById() {

        //Given
        User johnSmith = new User();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        User oskarLood = new User();
        oskarLood.setName("Oskar");
        oskarLood.setLastName("Lood");
        oskarLood.setPesel(337234232);
        oskarLood.setPhone(337499232);
        oskarLood.setBlocked(true);

        userRepository.save(johnSmith);
        userRepository.save(oskarLood);

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

        Rent rent1 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(1), johnSmith, mlot);
        Rent rent2 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(2), oskarLood, koparka);
        rentRepository.save(rent1);
        rentRepository.save(rent2);

        //When
        Long rent1Id = rent1.getId();
        Long rent2Id = rent2.getId();

        Optional<Rent> testRent1 = rentRepository.findById(rent1Id);
        Optional<Rent> testRent2 = rentRepository.findById(rent2Id);

        //Then
        assertEquals(rent1Id, testRent1.get().getId());
        assertEquals(rent2Id, testRent2.get().getId());

        //CleanUp
        rentRepository.deleteAll();
        userRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    void testDeleteRentById() {

        //Given
        User johnSmith = new User();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        User oskarLood = new User();
        oskarLood.setName("Oskar");
        oskarLood.setLastName("Lood");
        oskarLood.setPesel(337234232);
        oskarLood.setPhone(337499232);
        oskarLood.setBlocked(true);

        userRepository.save(johnSmith);
        userRepository.save(oskarLood);

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

        Rent rent1 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(1), johnSmith, mlot);
        Rent rent2 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(2), oskarLood, koparka);
        rentRepository.save(rent1);
        rentRepository.save(rent2);

        //When
        rentRepository.deleteById(rent2.getId());
        List<Rent> rents = (List<Rent>) rentRepository.findAll();

        //Then
        assertEquals(1, rents.size());
        assertEquals(rent1.getId(), rents.get(0).getId());

        //CleanUp
        rentRepository.deleteAll();
        userRepository.deleteAll();
        itemRepository.deleteAll();

    }

}
