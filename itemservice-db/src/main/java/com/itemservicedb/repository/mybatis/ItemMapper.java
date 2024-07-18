package com.itemservicedb.repository.mybatis;

import com.itemservicedb.domain.Item;
import com.itemservicedb.repository.ItemSearchCond;
import com.itemservicedb.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(Item item);

    //파라미터가 2개가 넘어가면 파라미터 어노테이션 추가
    void update(@Param("id") Long id, @Param("update") ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond itemSearchCond);
}
