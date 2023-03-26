package com.end_project.controller;

import com.end_project.domain.User;
import com.end_project.domain.UserDto;
import com.end_project.exception.UserNotFoundException;
import com.end_project.mapper.UserMapper;
import com.end_project.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserDbService userDbService;
    private final UserMapper userMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userDbService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}")
    public ResponseEntity<UserDto> blockUser(@PathVariable Long userId) throws UserNotFoundException {
        User user = userDbService.getUser(userId);
        user.setBlocked(true);
        User savedUser = userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(savedUser));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userDbService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
