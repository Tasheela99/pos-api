package com.pos.posapi.service;


import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.requestdto.RequestItemSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface ItemService {
    CommonResponseDTO createItem(int categoryId,int stockId, RequestItemSaveDto itemDto);

    CommonResponseDTO updateItem(int id, RequestItemSaveDto itemDto);

    CommonResponseDTO deleteItem(int id);

    List<ItemDto> getItems();

    ItemDto getItemById(int id);
}
