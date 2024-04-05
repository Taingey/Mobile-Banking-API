package com.istad.banking.util;

import java.util.Objects;

public class MediaUtil {
    public static String extractExtension(String mediaName){
        int lastDotIndex = mediaName.lastIndexOf(".");
        return mediaName.substring(lastDotIndex + 1);
    }
}
