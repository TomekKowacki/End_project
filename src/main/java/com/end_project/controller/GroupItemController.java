package com.end_project.controller;

import com.end_project.domain.GroupItem;
import com.end_project.domain.GroupItemDto;
import com.end_project.exception.GroupNotFoundException;
import com.end_project.mapper.GroupMapper;
import com.end_project.service.GroupItemDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/group")
public class GroupItemController {

    private final GroupItemDbService groupItemDbService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupItemDto>> getItemGroups() {
        List<GroupItem> groupProducts = groupItemDbService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupItemDtoList(groupProducts));
    }

    @GetMapping(value = "/{groupId}")
    public ResponseEntity<GroupItemDto> getProductGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupItemDto(groupItemDbService.getGroupItem(groupId)));
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupItemDto groupItemDto) {
        GroupItem groupItem = groupMapper.mapToGroupItem(groupItemDto);
        groupItemDbService.saveGroupItem(groupItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<GroupItemDto> updateItemGroup(@RequestBody GroupItemDto groupItemDto) {
        GroupItem groupItem = groupMapper.mapToGroupItem(groupItemDto);
        GroupItem savedGroupItem = groupItemDbService.saveGroupItem(groupItem);
        return ResponseEntity.ok(groupMapper.mapToGroupItemDto(savedGroupItem));
    }

    @DeleteMapping(value = "/{groupId}")
    public ResponseEntity<Object> deleteGroupProduct(@PathVariable Long groupId) throws GroupNotFoundException {
        if(groupItemDbService.getGroupItem(groupId).getItems().isEmpty()){
            groupItemDbService.deleteGroupItem(groupId);}
        return ResponseEntity.ok().build();
    }
}
