package org.example.itemservice.Service;

import lombok.AllArgsConstructor;
import org.example.itemservice.Dao.Entity.Item;
import org.example.itemservice.Dao.Entity.Picture;

import org.example.itemservice.Dao.Repository.ItemRepository;
import org.example.itemservice.Dao.Repository.PictureRepository;
import org.example.itemservice.Dto.ItemDto;
import org.example.itemservice.Dto.PictureDto;
import org.example.itemservice.mapper.ItemMapper;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

import reactor.core.scheduler.Schedulers;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final PictureRepository pictureRepository;
    private final ItemMapper itemMapper;

//    @Override
//    public Mono<ItemDto> createItem(Mono<ItemDto> itemDtoMono, Flux<FilePart> pictures) {
//        return itemDtoMono.flatMap(dto -> {
//            Item item = itemMapper.fromItemDtoToItem(dto);
//            Date CreationDate = new Date();
//            item.setCreatedAt(CreationDate);
//            item.setUpdatedAt(CreationDate);
//
//            // First, save the item to get its ID
//            return Mono.fromCallable(() -> itemRepository.save(item))
//                    .subscribeOn(Schedulers.boundedElastic())
//                    .flatMap(savedItem -> {
//                        if (pictures == null) {
//                            // If no pictures, return the saved item as DTO
//                            return Mono.just(itemMapper.fromItemToItemDto(savedItem));
//                        }
//
//                        // If pictures exist, save each picture
//                        return pictures
//                                .flatMap(filePart -> {
//                                    String fileName = filePart.filename();
//                                    String directoryPath = "src/main/resources/static/images/" + savedItem.getId();
//                                    Path directory = Path.of(directoryPath);
//
//                                    // Ensure directory exists
//                                    try {
//                                        Files.createDirectories(directory);
//                                    } catch (IOException e) {
//                                        return Mono.error(new RuntimeException("Could not create directory for storing images", e));
//                                    }
//
//                                    // Define the full path to save the file
//                                    Path filePath = directory.resolve(fileName);
//
//                                    // Save the file
//                                    return filePart.transferTo(filePath)
//                                            .then(Mono.fromCallable(() -> {
//                                                Picture picture = Picture.builder()
//                                                        .url("/images/" + savedItem.getId() + "/" + fileName) // URL for accessing the image
//                                                        .name(fileName)
//                                                        .item(savedItem)
//                                                        .build();
//                                                return pictureRepository.save(picture);
//                                            }).subscribeOn(Schedulers.boundedElastic()));
//                                })
//                                .collectList()
//                                .flatMap(savedPictures -> {
//                                    // Reload the item to include the latest pictures
//                                    return Mono.fromCallable(() -> itemRepository.findById(savedItem.getId()).orElse(null))
//                                            .subscribeOn(Schedulers.boundedElastic())
//                                            .map(itemMapper::fromItemToItemDto);
//                                });
//                    });
//        });
//    }



    // In your service
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/images/";

    @Override
    public Mono<ItemDto> createItem(Mono<ItemDto> itemDtoMono, Flux<FilePart> pictures) {
        return itemDtoMono.flatMap(dto -> {
            Item item = itemMapper.fromItemDtoToItem(dto);
            Date creationDate = new Date();
            item.setCreatedAt(creationDate);
            item.setUpdatedAt(creationDate);

            return Mono.fromCallable(() -> itemRepository.save(item))
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(savedItem -> {
                        if (pictures == null) {
                            return Mono.just(itemMapper.fromItemToItemDto(savedItem));
                        }

                        return pictures
                                .flatMap(filePart -> {
                                    String fileName = System.currentTimeMillis() + "_" + filePart.filename();
                                    String directoryPath = UPLOAD_DIR + savedItem.getId();
                                    Path directory = Path.of(directoryPath);

                                    try {
                                        Files.createDirectories(directory);
                                    } catch (IOException e) {
                                        return Mono.error(new RuntimeException("Could not create directory", e));
                                    }

                                    Path filePath = directory.resolve(fileName);
                                    return filePart.transferTo(filePath)
                                            .then(Mono.fromCallable(() -> {
                                                Picture picture = Picture.builder()
                                                        .url("/api/items/images/" + savedItem.getId() + "/" + fileName)  // ← Fixed this line
                                                        .name(fileName)
                                                        .item(savedItem)
                                                        .build();
                                                return pictureRepository.save(picture);
                                            }).subscribeOn(Schedulers.boundedElastic()));
                                })
                                .collectList()
                                .flatMap(savedPictures -> {
                                    return Mono.fromCallable(() -> itemRepository.findById(savedItem.getId()).orElse(null))
                                            .subscribeOn(Schedulers.boundedElastic())
                                            .map(itemMapper::fromItemToItemDto);
                                });
                    });
        });
    }


    @Override
    public ItemDto getItemById(long id) {
        Item item = itemRepository.findById(id).orElse(null);
        return itemMapper.fromItemToItemDto(item);

    }



    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        // Map each Exchange entity to an ExchangeDto using the ExchangeMapper
        return items.stream()
                .map(itemMapper::fromItemToItemDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }


    @Override
    public List<ItemDto> findByUserId (String userId){
        List<Item> items = itemRepository.findByUserId(userId);
        return items.stream()
                .map(itemMapper::fromItemToItemDto)
                .collect(Collectors.toList());
    }



}