package com.srk.springboot.emailservice.controller;
import com.srk.springboot.emailservice.model.EmailUser;
import com.srk.springboot.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
public class EmailController {
    final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/sendEmail")
    @ResponseBody
    public String sendMail(@RequestBody EmailUser user,@RequestBody String emailTemplate)throws MessagingException {
        try {
            if(isValidRequest(user,emailTemplate))
            emailService.sendMail(user,emailTemplate);
        } catch (Exception e) {
          // TODO need to implement exception handling
            e.printStackTrace();
            return "Error while sending email.!";
        }
        return "Email Sent Successfully.!";
    }
    private boolean isValidRequest(EmailUser user,String emailTemplate) throws Exception {
        // TODO can be validated with user management services
        // for the scope of test checking user name and email not null
        // also custom Exception classes need to be implemented
        if(user.getName()==null || user.getEmailId()==null)
            throw new Exception("Invalid user/eMail");
        if (emailTemplate==null)
            throw new Exception("Invalid Template");
        else return true;
    }
}
