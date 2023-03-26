package com.end_project.service;

import com.end_project.domain.User;
import com.end_project.exception.UserNotFoundException;
import com.end_project.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepo repository;

    public User getUser(Long userId) throws UserNotFoundException {
        return repository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(User user) {
        return repository.save(user);
    }
}
