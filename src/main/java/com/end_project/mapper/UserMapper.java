package com.end_project.mapper;

import com.end_project.domain.Rent;
import com.end_project.domain.User;
import com.end_project.domain.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastName(),
                userDto.getPesel(),
                userDto.getPhone(),
                userDto.isBlocked(),
                userDto.getRentsList()
        );
    }

    public UserDto mapToUserDto(User user) {
        List<Rent> rentsList = new ArrayList<>();

        if (!user.getRents().isEmpty()) {
            rentsList = user.getRents();
        }

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getPesel(),
                user.getPhone(),
                user.isBlocked(),
                rentsList
        );
    }
}
