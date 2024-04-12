package com.istad.banking.feature.mail;

import com.istad.banking.feature.mail.dto.MailRequest;
import com.istad.banking.feature.mail.dto.MailResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MailResponse send(@Valid @RequestBody MailRequest mailRequest){
        return mailService.send(mailRequest);
    }
}
