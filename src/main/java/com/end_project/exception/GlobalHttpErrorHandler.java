package com.end_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalHttpErrorHandler {

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleGroupNotFoundException(GroupNotFoundException exception) {
        return new ResponseEntity<>("Item group with given id doesn't exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(ItemNotFoundException exception) {
        return new ResponseEntity<>("Item with given id doesn't exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentNotFoundException.class)
    public ResponseEntity<Object> handleRentNotFoundException(RentNotFoundException exception) {
        return new ResponseEntity<>("Rent with given id doesn't exist", HttpStatus.NOT_FOUND);
    }
}
