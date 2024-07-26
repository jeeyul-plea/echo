package com.practice.spring.echo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class examComponent {
    @Scheduled(cron ="0/5 * * * * *" )
    public void schedule(){
        System.out.println("this is schedule");
    }
}
