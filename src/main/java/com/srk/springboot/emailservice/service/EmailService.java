package com.srk.springboot.emailservice.service;

import com.srk.springboot.emailservice.model.EmailUser;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Date;

import static org.quartz.TriggerBuilder.newTrigger;

@Service
public class EmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    public EmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }
    @Autowired
    JobDetail jobADetails;
    public SimpleTrigger scheduleMail(EmailUser user, String template, Date schTime){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("user",user);
        jobDataMap.put("template",template);
        return (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(schTime) // some Date
                .forJob("jobADetails") // identify job with name, group strings
                .usingJobData(jobDataMap)
                .build();
    }

    public void sendMail(EmailUser user,String template) throws MessagingException,Exception {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("user", user);

        String process = templateEngine.process(template, thymeleafContext);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject("Dear " + user.getName());
        mimeMessageHelper.setText(process, true);
        mimeMessageHelper.setTo(user.getEmailId());
        //TODO Just printing for current scope , need to handle logging
        System.out.println(mimeMessage.getContent());
        javaMailSender.send(mimeMessage);
    }
}