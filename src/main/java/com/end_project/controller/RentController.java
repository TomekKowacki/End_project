package com.end_project.controller;

import com.end_project.domain.Rent;
import com.end_project.domain.RentDto;
import com.end_project.exception.RentNotFoundException;
import com.end_project.mapper.RentMapper;
import com.end_project.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rent")
public class RentController {

    private final RentDbService rentDbService;
    private final RentMapper rentMapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents(){
        List<Rent> rents = rentDbService.getAllRents();
        return ResponseEntity.ok(rentMapper.mapToRentDtoList(rents));
    }

    @PostMapping
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto){
        Rent rent = rentMapper.mapToRent(rentDto);
        rentDbService.saveRent(rent);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
        return ResponseEntity.ok(rentMapper.mapToRentDto(rentDbService.getRent(rentId)));
    }

    @PutMapping
    public ResponseEntity<RentDto> updateRent(@RequestBody RentDto rentDto) {
        Rent rent = rentMapper.mapToRent(rentDto);
        Rent savedRent = rentDbService.saveRent(rent);
        return ResponseEntity.ok(rentMapper.mapToRentDto(savedRent));
    }

    @DeleteMapping(value = "/{rentId}")
    public ResponseEntity<Object> deleteRent(@PathVariable Long rentId) throws RentNotFoundException {
        rentDbService.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }
}
