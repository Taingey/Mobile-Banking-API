package com.istad.banking.feature.media.controller;

import com.istad.banking.feature.media.dto.MediaResponse;
import com.istad.banking.feature.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService service;
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file) {
        return service.uploadSingle(file, "IMAGES");
    }

}
