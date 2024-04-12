package com.istad.banking.feature.mail;

import com.istad.banking.feature.mail.dto.MailRequest;
import com.istad.banking.feature.mail.dto.MailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailServiceImpl implements MailService{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Override
    public MailResponse send(MailRequest mailRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        Context context = new Context();
        context.setVariable("name", "ISTAD");
        String htmlTemplate = templateEngine.process("mail/sample", context);

        try {
            mimeMessageHelper.setTo(mailRequest.to());
            mimeMessageHelper.setSubject(mailRequest.subject());
            mimeMessageHelper.setText(htmlTemplate, true);
//            Path imagePath = Paths.get("D:\\Anime Pic\\fanart1.jpg");
//            byte[] imageBytes = Files.readAllBytes(imagePath);
//
//            // Attach image
//            mimeMessageHelper.addAttachment("image.jpg", new ByteArrayResource(imageBytes), "image/jpeg");

        } catch (MessagingException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something went wrong"
                    );
        }

        javaMailSender.send(mimeMessage);
        return new MailResponse("Mail has been sand");
    }
}
