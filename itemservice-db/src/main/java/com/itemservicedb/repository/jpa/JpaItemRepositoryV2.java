package com.itemservicedb.repository.jpa;

import com.itemservicedb.domain.Item;
import com.itemservicedb.repository.ItemRepository;
import com.itemservicedb.repository.ItemSearchCond;
import com.itemservicedb.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepositoryV2 implements ItemRepository {

    private final SpringDataJpaItemRepository springDataJpaRepository;

    @Override
    public Item save(Item item) {
        return springDataJpaRepository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item item = springDataJpaRepository.findById(itemId).orElseThrow();
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return springDataJpaRepository.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        if (StringUtils.hasText(itemName) && maxPrice != null) {
            return springDataJpaRepository.findItems("%" + itemName + "%", maxPrice);
        } else if (StringUtils.hasText(itemName)) {
            return springDataJpaRepository.findByItemNameLike("%" + itemName + "%");
        } else if (maxPrice != null) {
            return springDataJpaRepository.findByPriceLessThanEqual(maxPrice);
        } else {
            return springDataJpaRepository.findAll();
        }
    }
}
