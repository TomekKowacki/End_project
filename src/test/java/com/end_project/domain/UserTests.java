package com.end_project.domain;

import com.end_project.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserTests {

    private UserRepo userRepository;

    @Test
    void testFindUserById() {

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

        //When
        Long johnSmithId = johnSmith.getId();
        Long oskarLoodId = oskarLood.getId();

        Optional<User> testUser1 = userRepository.findById(johnSmithId);
        Optional<User> testUser2 = userRepository.findById(oskarLoodId);

        //Then
        assertEquals(johnSmithId, testUser1.get().getId());
        assertEquals(oskarLoodId, testUser2.get().getId());

        //CleanUp
        userRepository.deleteAll();
    }

    @Test
    void testDeleteUserById() {

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

        //When
        userRepository.deleteById(johnSmith.getId());
        List<User> users = (List<User>) userRepository.findAll();

        //Then
        assertEquals(1, users.size());
        assertEquals(oskarLood.getId(), users.get(0).getId());

        //CleanUp
        userRepository.deleteAll();
    }
}
