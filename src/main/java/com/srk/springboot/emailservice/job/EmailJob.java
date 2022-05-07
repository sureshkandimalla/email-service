package com.srk.springboot.emailservice.job;

import com.srk.springboot.emailservice.model.EmailUser;
import com.srk.springboot.emailservice.service.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailJob implements Job {

    private final EmailService emailService;

    @Autowired

    public EmailJob(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void execute(JobExecutionContext context)  {

        try {
            emailService.sendMail((EmailUser) context.getTrigger().getJobDataMap().get("user"),(String)context.getTrigger().getJobDataMap().get("template"));
        } catch (Exception e) {
            //TODO add Exception handling
            e.printStackTrace();
        }
        System.out.println("Running JOb");
    }
}