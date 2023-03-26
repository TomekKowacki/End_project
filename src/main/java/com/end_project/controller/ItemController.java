package com.end_project.controller;

import com.end_project.domain.Item;
import com.end_project.domain.ItemDto;
import com.end_project.exception.GroupNotFoundException;
import com.end_project.exception.ItemNotFoundException;
import com.end_project.mapper.ItemMapper;
import com.end_project.service.GroupItemDbService;
import com.end_project.service.ItemDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/item")
public class ItemController {

    private final ItemDbService itemDbService;
    private final ItemMapper itemMapper;
    private final GroupItemDbService groupItemDbService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(){
        List<Item> items = itemDbService.getAllItems();
        return ResponseEntity.ok(itemMapper.mapToItemDtoList(items));
    }

    @GetMapping(value = "/{itemId}")
    public ResponseEntity<ItemDto> getItem(@PathVariable Long itemId) throws ItemNotFoundException {
        return ResponseEntity.ok(itemMapper.mapToItemDto(itemDbService.getItem(itemId)));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ItemDto itemDto) throws GroupNotFoundException {
        Item item = itemMapper.mapToItem(itemDto);
        Long groupItemId = itemDto.getGroupItemId();
        if (groupItemId != null) {
            item.setGroupItem(groupItemDbService.getGroupItem(groupItemId));
        }
        itemDbService.saveItem(item);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ItemDto> updateItem(@RequestBody ItemDto itemDto){
        Item item = itemMapper.mapToItem(itemDto);
        Item savedItem = itemDbService.saveItem(item);
        return ResponseEntity.ok(itemMapper.mapToItemDto(savedItem));
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long itemId) throws ItemNotFoundException {
        itemDbService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }
}
