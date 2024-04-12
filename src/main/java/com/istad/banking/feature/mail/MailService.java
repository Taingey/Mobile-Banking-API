package com.istad.banking.feature.mail;

import com.istad.banking.feature.mail.dto.MailRequest;
import com.istad.banking.feature.mail.dto.MailResponse;

public interface MailService {
    MailResponse send(MailRequest mailRequest);
}
