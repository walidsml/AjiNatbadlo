package org.example.itemservice.Service;

import org.example.itemservice.Dto.ItemRequest;
import org.example.itemservice.Dto.ItemResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    ItemResponse createItem(ItemRequest itemRequest, List<MultipartFile> files) throws IOException;

    ItemResponse getItemById(long id);

    List<ItemResponse> getAllItems();

    void deleteItem(long id);
}