package com.istad.banking.feature.media;

import com.istad.banking.feature.media.dto.MediaResponse;
import com.istad.banking.feature.media.service.MediaService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

        String newName = UUID.randomUUID().toString();

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

}
