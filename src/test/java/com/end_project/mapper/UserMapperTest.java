package com.end_project.mapper;

import com.end_project.domain.User;
import com.end_project.domain.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserMapperTest {

    private UserMapper userMapper;

    @Test
    void testMapToUserDto() {
        //Given
        User johnSmith = new User();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        UserDto johnSmithDto = new UserDto();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        //When
        UserDto testUser = userMapper.mapToUserDto(johnSmith);
        //Then
        assertEquals(johnSmithDto.getId(), testUser.getId());
        assertEquals(johnSmithDto.getName(), testUser.getName());
        assertEquals(johnSmithDto.getLastName(), testUser.getLastName());
        assertEquals(johnSmithDto.getPesel(), testUser.getPesel());
        assertEquals(johnSmithDto.getPhone(), testUser.getPhone());
    }

    @Test
    void testMapToUser() {
        //Given
        User johnSmith = new User();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        UserDto johnSmithDto = new UserDto();
        johnSmith.setName("John");
        johnSmith.setLastName("Smith");
        johnSmith.setPesel(920394223);
        johnSmith.setPhone(993004884);
        johnSmith.setBlocked(false);

        //When
        User testUser = userMapper.mapToUser(johnSmithDto);
        //Then
        assertEquals(johnSmith.getId(), testUser.getId());
        assertEquals(johnSmith.getName(), testUser.getName());
        assertEquals(johnSmith.getLastName(), testUser.getLastName());
        assertEquals(johnSmith.getPesel(), testUser.getPesel());
        assertEquals(johnSmith.getPhone(), testUser.getPhone());
    }
}
