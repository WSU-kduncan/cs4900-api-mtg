package com.mtg.mtgservice.service;


import org.springframework.stereotype.Service;

import com.mtg.mtgservice.dto.ListsDto;


import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;
import com.mtg.mtgservice.repository.ListsRepository;
import com.mtg.mtgservice.mapper.ListsDtoMapper;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ListsService {
    private final ListsRepository listsRepository;
    private final ListsDtoMapper listDtoMapper;

    public List<com.mtg.mtgservice.model.Lists> getAllLists() {
        return listsRepository.findAll();
    }

    public com.mtg.mtgservice.model.Lists getListById(Integer listID) throws EntityNotFoundException {
        return listsRepository
            .findById(listID)
            .orElseThrow();
    }

    public com.mtg.mtgservice.model.Lists updateList(com.mtg.mtgservice.model.Lists list) {
        if (!listsRepository.existsById(list.getListID())) {
            throw new EntityNotFoundException("List not found");
        }
        return listsRepository.save(list);
    }

    public com.mtg.mtgservice.model.Lists createList(ListsDto listDto) throws EntityNotFoundException{
        return listsRepository.saveAndFlush(listDtoMapper.toEntity(listDto));
    }

    public com.mtg.mtgservice.model.Lists getCustomerByEmail(String customerEmail) {
        return listsRepository.findByCustomerEmail_CustomerEmail(customerEmail)
            .orElseThrow(() -> new EntityNotFoundException("List not found"));
    }
}