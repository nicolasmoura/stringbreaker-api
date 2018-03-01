package org.nicolas.stringbreaker.tasks;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class HerokuKeepAliveTask {
    @Scheduled(cron = "00,15,30,45 8-18 * * 1-5")
    public void keepAlive() {
        try {
            Unirest.get("https://stringbreaker.herokuapp.com").asString();
            Unirest.get("https://stringbreaker-api.herokuapp.com").asString();
            System.out.println("Sending keepAlive request to Heroku at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
