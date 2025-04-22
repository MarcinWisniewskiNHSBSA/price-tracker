package com.nhs.band6assignment.service;

import com.nhs.band6assignment.model.ProductAlert;
import java.util.TimeZone;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class ProductAlertSenderService {
  private final TaskScheduler taskScheduler;
  private final RestTemplate restTemplate;

  public void scheduleAlert(String cron, ProductAlert productAlert) {
    log.info("Scheduling a new alert");
    taskScheduler.schedule(
        () -> {
          String response =
              restTemplate.postForObject(
                  "http://localhost:8080/track/check-price", productAlert, String.class);
          log.info(response);
        },
        new CronTrigger(cron, TimeZone.getTimeZone(TimeZone.getDefault().toZoneId())));
  }
}
