package org.example.itemservice.Web;

import org.example.itemservice.Dto.ItemRequest;
import org.example.itemservice.Dto.ItemResponse;
import org.example.itemservice.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createItem(
            @RequestPart("item") ItemRequest itemRequest,
            @RequestPart("pictures") List<MultipartFile> files) {
        try {
            ItemResponse itemResponse = itemService.createItem(itemRequest, files);
            return new ResponseEntity<>(itemResponse, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error while uploading files: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems() {

        System.out.println("getAllItems");
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
