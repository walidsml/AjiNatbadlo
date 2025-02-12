package org.example.itemservice.Service;

import lombok.AllArgsConstructor;
import org.example.itemservice.Dao.Entity.Item;
import org.example.itemservice.Dao.Entity.Picture;

import org.example.itemservice.Dao.Repository.ItemRepository;
import org.example.itemservice.Dto.ItemRequest;
import org.example.itemservice.Dto.ItemResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;



    @Override
    public ItemResponse createItem(ItemRequest itemRequest, List<MultipartFile> files) throws IOException {
        // Create the Item
        Item item = Item.builder()
                .title(itemRequest.getTitle())
                .description(itemRequest.getDescription())
                .category(itemRequest.getCategory())
                .preferences(itemRequest.getPreferences())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        // Handle picture uploads
        List<Picture> pictures = new ArrayList<>();
        for (MultipartFile file : files) {
            // Save file to server (or cloud storage)
            String uploadDir = "/path/to/upload/directory/"; // Replace with your directory
            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // Create Picture entity
            pictures.add(Picture.builder()
                    .url("http://your-server.com/uploads/" + file.getOriginalFilename()) // Replace with your server URL
                    .name(file.getOriginalFilename())
                    .item(item)
                    .build());
        }

        // Associate pictures with the item
        item.setPictures(pictures);

        // Save the item and return the response
        Item savedItem = itemRepository.save(item);
        return toItemResponse(savedItem);
    }

    @Override
    public ItemResponse getItemById(long id) {
        return itemRepository.findById(id)
                .map(this::toItemResponse)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    private ItemResponse toItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .category(item.getCategory())
                .preferences(item.getPreferences())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .pictureUrls( // Map Picture entities to their URLs
                        item.getPictures().stream()
                                .map(Picture::getUrl)
                                .collect(Collectors.toList())
                )
                .build();
    }

}