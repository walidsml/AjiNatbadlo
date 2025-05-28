package org.example.itemservice.Web;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/items/images")
public class ImageController {

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/images/";

    @GetMapping("/{itemId}/{filename}")
    public Mono<ResponseEntity<Resource>> getImage(
            @PathVariable String itemId,
            @PathVariable String filename) {

        return Mono.fromCallable(() -> {
                    Path imagePath = Path.of(UPLOAD_DIR + itemId + "/" + filename);

                    if (!Files.exists(imagePath)) {
                        return null;
                    }

                    Resource resource = new FileSystemResource(imagePath);

                    String contentType = "application/octet-stream";
                    try {
                        contentType = Files.probeContentType(imagePath);
                        if (contentType == null) {
                            contentType = "application/octet-stream";
                        }
                    } catch (IOException e) {
                        // Use default content type
                    }

                    return ResponseEntity.ok()
                            .contentType(MediaType.parseMediaType(contentType))
                            .body(resource);

                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(response -> response != null ? response : ResponseEntity.<Resource>notFound().build());
    }

    // New endpoint to list all images
    @GetMapping("/list")
    public Mono<ResponseEntity<Map<String, Object>>> getAllImages() {
        return Mono.fromCallable(() -> {
            Map<String, Object> result = new HashMap<>();
            List<Map<String, Object>> allImages = new ArrayList<>();

            try {
                Path uploadPath = Path.of(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    result.put("message", "Upload directory does not exist");
                    result.put("uploadDirectory", UPLOAD_DIR);
                    result.put("images", allImages);
                    return ResponseEntity.ok(result);
                }

                // Iterate through each item directory
                try (DirectoryStream<Path> itemDirectories = Files.newDirectoryStream(uploadPath, Files::isDirectory)) {
                    for (Path itemDir : itemDirectories) {
                        String itemId = itemDir.getFileName().toString();

                        // Get all image files in this item directory
                        try (DirectoryStream<Path> imageFiles = Files.newDirectoryStream(itemDir, "*.{jpg,jpeg,png,gif,bmp,webp}")) {
                            for (Path imageFile : imageFiles) {
                                Map<String, Object> imageInfo = new HashMap<>();
                                imageInfo.put("itemId", itemId);
                                imageInfo.put("filename", imageFile.getFileName().toString());
                                imageInfo.put("url", "/api/items/images/" + itemId + "/" + imageFile.getFileName().toString());
                                imageInfo.put("fullUrl", "http://localhost:8080/api/items/images/" + itemId + "/" + imageFile.getFileName().toString());
                                imageInfo.put("filePath", imageFile.toString());
                                imageInfo.put("fileSize", Files.size(imageFile));
                                imageInfo.put("lastModified", Files.getLastModifiedTime(imageFile).toString());

                                allImages.add(imageInfo);
                            }
                        }
                    }
                }

                result.put("message", "Images retrieved successfully");
                result.put("uploadDirectory", UPLOAD_DIR);
                result.put("totalImages", allImages.size());
                result.put("images", allImages);

                return ResponseEntity.ok(result);

            } catch (IOException e) {
                result.put("error", "Error reading upload directory: " + e.getMessage());
                result.put("uploadDirectory", UPLOAD_DIR);
                result.put("images", allImages);
                return ResponseEntity.ok(result);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    // New endpoint to list images for a specific item
    @GetMapping("/list/{itemId}")
    public Mono<ResponseEntity<Map<String, Object>>> getImagesByItemId(@PathVariable String itemId) {
        return Mono.fromCallable(() -> {
            Map<String, Object> result = new HashMap<>();
            List<Map<String, Object>> itemImages = new ArrayList<>();

            try {
                Path itemPath = Path.of(UPLOAD_DIR + itemId);

                if (!Files.exists(itemPath)) {
                    result.put("message", "No images found for item " + itemId);
                    result.put("itemId", itemId);
                    result.put("itemDirectory", itemPath.toString());
                    result.put("images", itemImages);
                    return ResponseEntity.ok(result);
                }

                // Get all image files for this item
                try (DirectoryStream<Path> imageFiles = Files.newDirectoryStream(itemPath, "*.{jpg,jpeg,png,gif,bmp,webp}")) {
                    for (Path imageFile : imageFiles) {
                        Map<String, Object> imageInfo = new HashMap<>();
                        imageInfo.put("filename", imageFile.getFileName().toString());
                        imageInfo.put("url", "/api/items/images/" + itemId + "/" + imageFile.getFileName().toString());
                        imageInfo.put("fullUrl", "http://localhost:8080/api/items/images/" + itemId + "/" + imageFile.getFileName().toString());
                        imageInfo.put("filePath", imageFile.toString());
                        imageInfo.put("fileSize", Files.size(imageFile));
                        imageInfo.put("lastModified", Files.getLastModifiedTime(imageFile).toString());

                        itemImages.add(imageInfo);
                    }
                }

                result.put("message", "Images retrieved successfully for item " + itemId);
                result.put("itemId", itemId);
                result.put("itemDirectory", itemPath.toString());
                result.put("totalImages", itemImages.size());
                result.put("images", itemImages);

                return ResponseEntity.ok(result);

            } catch (IOException e) {
                result.put("error", "Error reading item directory: " + e.getMessage());
                result.put("itemId", itemId);
                result.put("images", itemImages);
                return ResponseEntity.ok(result);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}