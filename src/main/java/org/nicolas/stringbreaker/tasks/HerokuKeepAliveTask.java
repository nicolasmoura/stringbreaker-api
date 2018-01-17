package org.nicolas.stringbreaker.tasks;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HerokuKeepAliveTask {
    @Scheduled(fixedRate = 300000)
    public void keepAlive() {
        try {
            Unirest.get("https://stringbreaker.herokuapp.com").asString();
            Unirest.get("https://stringbreaker-api.herokuapp.com").asString();
            System.out.println("Sending keepAlive request to Heroku: DONE");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
