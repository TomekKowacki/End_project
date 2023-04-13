package com.end_project.mapper;

import com.end_project.domain.Item;
import com.end_project.domain.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemMapperTests {

    private ItemMapper itemMapper;

    @Test
    void testMapToItemDto() {
        //Given
        Item mlot = new Item();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        ItemDto mlotDto = new ItemDto();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        //When
        ItemDto testItemDto = itemMapper.mapToItemDto(mlot);
        //Then
        assertEquals(mlotDto.getId(), testItemDto.getId());
        assertEquals(mlotDto.getName(), testItemDto.getName());
        assertEquals(mlotDto.getDescription(), testItemDto.getDescription());
    }

    @Test
    void testMapToItem() {
        //Given
        Item mlot = new Item();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        ItemDto mlotDto = new ItemDto();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        //When
        Item testItem = itemMapper.mapToItem(mlotDto);
        //Then
        assertEquals(mlot.getId(), testItem.getId());
        assertEquals(mlot.getName(), testItem.getName());
        assertEquals(mlot.getDescription(), testItem.getDescription());
    }

    @Test
    void testMapToItemListDto() {
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

        List<Item> items = new ArrayList<>();
        items.add(mlot);
        items.add(pila);
        items.add(koparka);

        ItemDto mlotDto = new ItemDto();
        mlot.setName("Młot");
        mlot.setPrice(new BigDecimal("120.49"));
        mlot.setDescription("Z udarem");

        ItemDto pilaDto = new ItemDto();
        pila.setName("Piła");
        pila.setPrice(new BigDecimal("60.49"));
        pila.setDescription("Do drewna");

        ItemDto koparkaDto = new ItemDto();
        koparka.setName("Koparka");
        koparka.setPrice(new BigDecimal("600.50"));
        koparka.setDescription("3,5T");

        List<ItemDto> itemsDto = new ArrayList<>();
        itemsDto.add(mlotDto);
        itemsDto.add(pilaDto);
        itemsDto.add(koparkaDto);


        //When
        List<ItemDto> testBikeDtoList = itemMapper.mapToItemDtoList(items);

        //Then
        assertEquals(itemsDto.get(0), testBikeDtoList.get(0));
        assertEquals(itemsDto.get(1), testBikeDtoList.get(1));
        assertEquals(itemsDto.size(), testBikeDtoList.size());
    }
}
