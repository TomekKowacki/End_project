package com.end_project.mapper;

import com.end_project.domain.GroupItem;
import com.end_project.domain.GroupItemDto;
import com.end_project.domain.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupMapper {

    public GroupItem mapToGroupItem(GroupItemDto groupItemDto) {
        GroupItem groupProduct = new GroupItem();
        groupProduct.setId(groupItemDto.getId());
        groupProduct.setName(groupItemDto.getName());
        return groupProduct;
    }

    public GroupItemDto mapToGroupItemDto(GroupItem groupItem) {
        List<String> products = groupItem.getItems().stream()
                .map(Item::getName)
                .collect(Collectors.toList());
        return new GroupItemDto(
                groupItem.getId(),
                groupItem.getName(),
                products);
    }

    public List<GroupItemDto> mapToGroupItemDtoList(List<GroupItem> groupProductList) {
        return groupProductList.stream()
                .map(this::mapToGroupItemDto)
                .collect(Collectors.toList());
    }
}
