package org.example.itemservice.mapper;

import org.example.itemservice.Dao.Entity.Item;
import org.example.itemservice.Dto.ItemDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ItemMapper {

    private final ModelMapper mapper = new ModelMapper();

    public Item fromItemDtoToItem(ItemDto itemDto) {
        return mapper.map(itemDto, Item.class);
    }
    public ItemDto fromItemToItemDto(Item item) {
        return mapper.map(item, ItemDto.class);
    }


}
