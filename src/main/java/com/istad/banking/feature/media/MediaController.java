package com.istad.banking.feature.media;

import com.istad.banking.feature.media.dto.MediaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService service;
    @PostMapping("/upload-single")
    MediaResponse uploadSingle(@RequestPart MultipartFile file) {
        return service.uploadSingle(file, "IMAGES");
    }

    @PostMapping("/upload-Multiple")
    public List<MediaResponse> uploadMultiple(@RequestPart List<MultipartFile> files){
        return  service.uploadMultiple(files, "IMAGES");
    }

    @GetMapping("/{mediaName}")
    public MediaResponse loadMedia(@PathVariable String mediaName){
        return  service.loadMediaByName(mediaName, "IMAGES");
    }

    @DeleteMapping("/{mediaName}")
    public MediaResponse deleteMedia(@PathVariable String mediaName){
        return  service.deleteMediaByName(mediaName, "IMAGES");
    }

    @GetMapping("/all")
    public List<MediaResponse> loadAllMedia(){
        return  service.loadAllMedia();
    }

    @GetMapping("/download/{mediaName}")
    public ResponseEntity<byte[]> downloadMediaByName(@PathVariable String mediaName){
        byte[] mediaContent = service.downloadMediaByName(mediaName);
        return  ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(mediaContent);
    }
}
