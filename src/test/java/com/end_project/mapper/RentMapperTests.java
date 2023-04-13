package com.end_project.mapper;

import com.end_project.domain.Item;
import com.end_project.domain.Rent;
import com.end_project.domain.RentDto;
import com.end_project.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentMapperTests {

    private RentMapper rentMapper;

    @Test
    void testMapToRentDto() {
        //Given
        User johnSmith = new User();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        Item koparka = new Item();
        koparka.setName("Koparka");
        koparka.setPrice(new BigDecimal("600.50"));
        koparka.setDescription("3,5T");

        Rent rent1 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(1), johnSmith, koparka);
        //When
        RentDto rentDto = rentMapper.mapToRentDto(rent1);
        //Then
        assertEquals(rent1.getId(), rentDto.getId());
        assertEquals(rent1.getDateOfRent(), rentDto.getDateOfRent());
        assertEquals(rent1.getDateOfReturn(), rentDto.getDateOfReturn());
        assertEquals(rent1.getUser().getId(), rentDto.getUserId());
    }

    @Test
    void testMapToRent() {
        //Given
        User johnSmith = new User();
            johnSmith.setName("John");
            johnSmith.setLastName("Smith");
            johnSmith.setPesel(920394223);
            johnSmith.setPhone(993004884);
            johnSmith.setBlocked(false);

        Item koparka = new Item();
            koparka.setName("Koparka");
            koparka.setPrice(new BigDecimal("600.50"));
            koparka.setDescription("3,5T");

        List<Item> list = new ArrayList<>();
        list.add(koparka);

        RentDto rent1Dto = new RentDto(1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                "Nowogrodzka 23",
                list,
                johnSmith.getId());
        //When
        Rent testRent = rentMapper.mapToRent(rent1Dto);
        //Then
        assertEquals(rent1Dto.getId(), testRent.getId());
        assertEquals(rent1Dto.getDateOfRent(), testRent.getDateOfRent());
        assertEquals(rent1Dto.getDateOfReturn(), testRent.getDateOfReturn());
        assertEquals(rent1Dto.getUserId(), testRent.getUser().getId());

    }

    @Test
    void testMapToRentListDto() {
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

        Item mlot = new Item();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");
        List<Item> item1 = new ArrayList<>();
        item1.add(mlot);

        Item pila = new Item();
        pila.setName("Piła");
        pila.setPrice(new BigDecimal("60.49"));
        pila.setDescription("Do drewna");
        List<Item> item2 = new ArrayList<>();
        item2.add(pila);

        Rent rent1 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(1), johnSmith, mlot);
        Rent rent2 = new Rent(LocalDateTime.now(), LocalDateTime.now().plusDays(2), oskarLood, pila);
        List<Rent> rents = new ArrayList<>();
        rents.add(rent1);
        rents.add(rent2);

        RentDto rentDto1 = new RentDto(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(3),
                "Gdzieś 223", item1, johnSmith.getId());
        RentDto rentDto2 = new RentDto(2L, LocalDateTime.now(), LocalDateTime.now().plusDays(5),
                "Nigdzie 112", item2,  oskarLood.getId());

        List<RentDto> rentDtos = new ArrayList<>();
        rentDtos.add(rentDto1);
        rentDtos.add(rentDto2);

        //When
        List<RentDto> testList = rentMapper.mapToRentDtoList(rents);

        //Then
        assertEquals(testList.get(0), rentDtos.get(0));
        assertEquals(testList.get(1), rentDtos.get(1));
        assertEquals(testList.size(), rentDtos.size());
    }
}
