package com.end_project.mapper;

import com.end_project.domain.Item;
import com.end_project.domain.ItemDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemMapper {

    public Item mapToItem (ItemDto itemDto){
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setDescription(itemDto.getDescription());
        return item;
    }

    public ItemDto mapToItemDto(Item item) {

        Long groupItemId = 0L;

        if (item.getGroupItem() != null) {
            groupItemId = item.getGroupItem().getId();
        }

        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                groupItemId
        );
    }

    public List<Item> mapToItemList(List<ItemDto> itemDtoList) {
        return itemDtoList.stream()
                .map(this::mapToItem)
                .collect(Collectors.toList());
    }

    public List<ItemDto> mapToItemDtoList(List<Item> itemList) {
        return itemList.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());
    }
}
