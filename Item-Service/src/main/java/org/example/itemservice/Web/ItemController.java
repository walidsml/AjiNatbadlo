package org.example.itemservice.Web;

import org.example.itemservice.Dto.ItemDto;
import org.example.itemservice.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ItemDto> createItem(
            @RequestPart("item") Mono<ItemDto> itemDto,
            @RequestPart(value = "pictures", required = false) Flux<FilePart> pictures) {
        System.out.println("created");
        return itemService.createItem(itemDto, pictures);
    }






    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getAllItems() {

        System.out.println("getAllItems");
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ItemDto>> getItemByUserId(@PathVariable String id) {
        return ResponseEntity.ok(itemService.findByUserId(id)) ;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
