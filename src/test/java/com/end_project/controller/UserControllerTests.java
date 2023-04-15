package com.end_project.controller;

import com.end_project.domain.Rent;
import com.end_project.domain.User;
import com.end_project.domain.UserDto;
import com.end_project.exception.UserNotFoundException;
import com.end_project.mapper.UserMapper;
import com.end_project.service.UserDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
@WebMvcTest(UserController.class)
class UserControllerTests {

    @MockBean
    private UserDbService userService;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetUser() throws UserNotFoundException, Exception {
        //Given
        User johnSmith = new User(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>());

        UserDto johnSmithDto = new UserDto(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>());

        when(userService.getUser(1L)).thenReturn(johnSmith);
        when(userMapper.mapToUserDto(any())).thenReturn(johnSmithDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/{userId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("Smith")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(920394223)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", Matchers.is(993004884)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blocked", Matchers.is(false)));
    }

    @Test
    void testDeleteUser() throws UserNotFoundException, Exception {
        //Given
        User johnSmith = new User(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>());

        when(userService.saveUser(any())).thenReturn(johnSmith);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{userId}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        //Given
        UserDto johnSmithDto = new UserDto(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>());

        when(userService.saveUser(any())).thenReturn(new User(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>()));

        Gson gson = new Gson();
        String json = gson.toJson(johnSmithDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testUpdateUser() throws UserNotFoundException, Exception {
        //Given
        User user = new User(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>());

        UserDto userDto = new UserDto(1L, "John", "Smith",
                920394223, 993004884, false, new ArrayList<Rent>());

        UserDto updatedUser = new UserDto(1L, "John", "Smith",
                920394223, 345678923, true, new ArrayList<Rent>());

        when(userService.saveUser(user)).thenReturn(new User(1L, "John", "Smith",
                920394223, 345678923, true, new ArrayList<Rent>()));

        when(userMapper.mapToUserDto(any())).thenReturn(updatedUser);

        Gson gson = new Gson();
        String json = gson.toJson(userDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.is("Smith")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pesel", Matchers.is(920394223)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone", Matchers.is(345678923)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blocked", Matchers.is(true)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
