package org.example.itemservice.Service;

import org.example.itemservice.Dto.ItemDto;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ItemService {

    ItemDto getItemById(long id);
    List<ItemDto> getAllItems();
    void deleteItem(long id);
    Mono<ItemDto> createItem(Mono<ItemDto> itemDto, Flux<FilePart> pictures);

    List<ItemDto> findByUserId(String userId);

}