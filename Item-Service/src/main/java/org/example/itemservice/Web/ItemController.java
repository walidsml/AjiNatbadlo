package org.example.itemservice.Web;

import org.example.itemservice.Dto.ItemDto;
import org.example.itemservice.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;



import java.util.List;


@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private final ObjectMapper objectMapper;  // Add this field



    public ItemController(ItemService itemService) {
        this.itemService = itemService;
        this.objectMapper = new ObjectMapper();  // Initialize in constructor

    }


    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/images/";


    // Create item with multipart (JSON + pictures)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ItemDto> createItemMultipart(
            @RequestPart("item") String itemJson,
            @RequestPart(value = "pictures", required = false) Flux<FilePart> pictures) {

        System.out.println("Creating item via multipart");
        System.out.println("Item JSON: " + itemJson);

        try {
            ItemDto itemDto = objectMapper.readValue(itemJson, ItemDto.class);
            System.out.println("Parsed item: " + itemDto.getTitle());

            // Log picture count if present
            if (pictures != null) {
                return pictures.count()
                        .doOnNext(count -> System.out.println("Number of pictures: " + count))
                        .then(itemService.createItem(Mono.just(itemDto), pictures));
            } else {
                System.out.println("No pictures provided");
                return itemService.createItem(Mono.just(itemDto), null);
            }

        } catch (JsonProcessingException e) {
            System.err.println("JSON parsing error: " + e.getMessage());
            return Mono.error(new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid JSON format for item: " + e.getMessage()
            ));
        }
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
