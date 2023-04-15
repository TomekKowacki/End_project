package com.end_project.controller;

import com.end_project.domain.GroupItem;
import com.end_project.domain.Item;
import com.end_project.domain.ItemDto;
import com.end_project.domain.Rent;
import com.end_project.exception.ItemNotFoundException;
import com.end_project.mapper.ItemMapper;
import com.end_project.service.ItemDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@WebMvcTest(ItemController.class)
public class ItemControllerTests {

    @MockBean
    private ItemDbService itemService;
    @MockBean
    private ItemMapper itemMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetItems() throws Exception {
        //Given
        when(itemService.getAllItems()).thenReturn(List.of());

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/items/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void testGetBike() throws ItemNotFoundException, Exception {
        //Given
        Item item = new Item(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem(), new Rent());

        ItemDto itemDto = new ItemDto(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem().getId());

        when(itemService.getItem(1L)).thenReturn(item);
        when(itemMapper.mapToItemDto(any())).thenReturn(itemDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/items/{itemId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("racing")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is("120.49")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Z udarem")));
    }

    @Test
    void testDeleteBike() throws ItemNotFoundException, Exception {
        //Given
        Item item = new Item(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem(), new Rent());

        when(itemService.saveItem(any())).thenReturn(item);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/items/{itemId}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddBike() throws Exception {
        //Given
        ItemDto itemDto = new ItemDto(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem().getId());

        when(itemService.saveItem(any())).thenReturn(new Item(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem(), new Rent()));

        Gson gson = new Gson();
        String json = gson.toJson(itemDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateBike() throws ItemNotFoundException, Exception {
        //Given
        Item item = new Item(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem(), new Rent());

        ItemDto itemDto = new ItemDto(1L, "Młot", new BigDecimal("120.49"),
                "Z udarem", new GroupItem().getId());

        ItemDto updatedItem = new ItemDto(2L, "Młot", new BigDecimal("150.49"),
                "Z udarem", new GroupItem().getId());

        when(itemService.saveItem(item)).thenReturn(new Item(2L, "Młot", new BigDecimal("150.49"),
                "Z udarem", new GroupItem(), new Rent()));
        when(itemMapper.mapToItemDto(any())).thenReturn(updatedItem);

        Gson gson = new Gson();
        String json = gson.toJson(itemDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Młot")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", Matchers.is("150.49")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.is("Z udarem")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
