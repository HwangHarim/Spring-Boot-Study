package com.itemservicedb.service;

import com.itemservicedb.domain.Item;
import com.itemservicedb.repository.ItemSearchCond;
import com.itemservicedb.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);
}
