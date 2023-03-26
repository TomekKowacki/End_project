package com.end_project.mapper;

import com.end_project.domain.Rent;
import com.end_project.domain.RentDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentMapper {

    public Rent mapToRent (RentDto rentDto){
        Rent rent = new Rent();
        rent.setId(rentDto.getId());
        rent.setDateOfRent(rentDto.getDateOfRent());
        rent.setDateOfReturn(rentDto.getDateOfReturn());
        rent.setSiteAddres(rentDto.getSiteAddres());
        return rent;
    }

    public RentDto mapToRentDto (Rent rent){

        Long userId = 0L;

        if(rent.getUser() != null){
            userId = rent.getUser().getId();
        }

        return new RentDto(
                rent.getId(),
                rent.getDateOfRent(),
                rent.getDateOfReturn(),
                rent.getSiteAddres(),
                rent.getItem(),
                rent.getUser().getId()
        );
    }

    public List<Rent> mapToRentList(List<RentDto> rentDtoList){
        return rentDtoList.stream()
                .map(this::mapToRent)
                .collect(Collectors.toList());
    }

    public List<RentDto> mapToRentDtoList (List<Rent> rentList){
        return rentList.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
