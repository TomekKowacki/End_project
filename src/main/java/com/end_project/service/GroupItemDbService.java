package com.end_project.service;

import com.end_project.domain.GroupItem;
import com.end_project.exception.GroupNotFoundException;
import com.end_project.repository.GroupItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupItemDbService {

    private final GroupItemRepo groupRepository;

    public List<GroupItem> getAllGroups(){
        return groupRepository.findAll();
    }

    public GroupItem getGroupItem(Long id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    public GroupItem saveGroupItem(GroupItem groupItem) {
        return groupRepository.save(groupItem);
    }

    public void deleteGroupItem (Long id) throws GroupNotFoundException {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new GroupNotFoundException();
        }
    }
}
