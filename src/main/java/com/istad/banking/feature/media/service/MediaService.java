package com.istad.banking.feature.media.service;

import com.istad.banking.feature.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    MediaResponse uploadSingle(MultipartFile file, String folderName);
}
