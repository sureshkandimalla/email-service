package com.srk.springboot.emailservice.config;

import com.srk.springboot.emailservice.job.EmailJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailJobConfig {
        @Bean
        public JobDetail jobADetails() {
            return JobBuilder.newJob(EmailJob.class).withIdentity("emailJob").storeDurably().build();
        }
    }
