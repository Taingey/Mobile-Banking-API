package com.istad.banking.feature.media;

import com.istad.banking.feature.media.dto.MediaResponse;
import com.istad.banking.feature.media.service.MediaService;
import com.istad.banking.util.MediaUtil;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.base-uri}")
    private String baseUri;

    public MediaResponse uploadSingle(MultipartFile file, String folderName) {

        // Generate new unique name for file upload
        String newName = UUID.randomUUID().toString();

        // Extract extension from file upload
        // Assume profile.png
        int lastDotIndex = Objects.requireNonNull(file.getOriginalFilename())
                .lastIndexOf(".");
        String extension = file.getOriginalFilename()
                .substring(lastDotIndex + 1);
        newName = newName + "." + extension;


        Path path = Paths.get(serverPath + folderName + "\\" + newName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        }

        return MediaResponse.builder()
                .name(newName)
                .contentType(file.getContentType())
                .extension(extension)
                .size(file.getSize())
                .uri(String.format("%s%s/%s", baseUri, folderName, newName))
                .build();
    }

    @Override
    public List<MediaResponse> uploadMultiple(List<MultipartFile> files, String folderName) {
        List<MediaResponse> mediaResponses = new ArrayList<>();
        files.forEach(file -> {
            MediaResponse mediaResponse = this.uploadSingle(file, folderName);
            mediaResponses.add(mediaResponse);
        });
        return mediaResponses;
    }

    @Override
    public MediaResponse loadMediaByName(String mediaName, String folderName) {
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);

        try {
            Resource resource = new UrlResource(path.toUri());
            log.info("Load resource : {}", resource);
            if(!resource.exists()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Media has not been found!"
                        );
            }
            return MediaResponse.builder()
                    .name(resource.getFilename())
                    .contentType(Files.probeContentType(path))
                    .extension(MediaUtil.extractExtension(Objects.requireNonNull(resource.getFilename())))
                    .size(resource.contentLength())
                    .uri(String.format("%s%s/%s", baseUri, folderName, resource))
                    .build();
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getLocalizedMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MediaResponse deleteMediaByName(String mediaName, String folderName){
        Path path = Paths.get(serverPath + folderName + "\\" + mediaName);
        try {
            long size = Files.size(path);
            if (Files.deleteIfExists(path)) {
                return MediaResponse.builder()
                        .name(mediaName)
                        .contentType(Files.probeContentType(path))
                        .size(size)
                        .uri(String.format("%s%s/%s", baseUri, folderName, mediaName))
                        .build();
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Media has not been found!"
                    );
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadMediaByName(String mediaName) {
        Path path = Paths.get(serverPath + "IMAGES/" + mediaName);
        try {
            return Files.readAllBytes(path);
        }catch (IOException e){
            throw new RuntimeException("Failed to read media file: " + mediaName, e);
        }
    }

    @Override
    public List<MediaResponse> loadAllMedia() {
       try {
           List<MediaResponse> mediaResponses = new ArrayList<>();
           Files.walk(Path.of(serverPath + "IMAGES"))
                   .filter(Files::isRegularFile)
                   .forEach(file -> {
                       MediaResponse mediaResponse = MediaResponse.builder()
                               .name(file.getFileName().toString())
                               .uri(baseUri + "IMAGES/" + file.getFileName().toString())
                               .size(file.toFile().length())
                               .build();
                       mediaResponses.add(mediaResponse);
                   });
           return mediaResponses;
       }catch (IOException e){
           throw new  RuntimeException("Failed to load media files", e);
       }
    }
}
