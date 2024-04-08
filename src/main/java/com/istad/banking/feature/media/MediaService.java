package com.istad.banking.feature.media;

import com.istad.banking.feature.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file, String folderName);
    List<MediaResponse> uploadMultiple(List<MultipartFile> multipartFiles, String folderName);
    List<MediaResponse> loadAllMedia();
    MediaResponse loadMediaByName(String mediaName, String folderName);
    MediaResponse deleteMediaByName(String mediaName, String folderName);

    byte[] downloadMediaByName(String mediaName);
}
