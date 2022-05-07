package com.srk.springboot.emailservice.service;

import com.srk.springboot.emailservice.model.EmailUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EmailServiceTest {
    @Autowired
    EmailService emailService;

    @Test()
    void testInvalidUser() {
        EmailUser emailUser=new EmailUser();
        //Assert test the Exception type it sends because userName and eMail were empty
        //emailService.sendMail(emailUser));
    }
    @Test()
    void testSendMail() throws Exception {
        EmailUser emailUser=new EmailUser("Graham Williams","sureshkandimalla@gmail.com");
        emailService.sendMail(emailUser,"emailTemplate");
    }

    // we need to  write the rest of the -ve test cases

}