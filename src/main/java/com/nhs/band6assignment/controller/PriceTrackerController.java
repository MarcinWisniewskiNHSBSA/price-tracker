package com.nhs.band6assignment.controller;

import com.nhs.band6assignment.exception.ProductNotFound;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.model.ProductNewAlert;
import com.nhs.band6assignment.service.ProductCheckPriceService;
import com.nhs.band6assignment.service.ProductNewAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
@RequiredArgsConstructor
public class PriceTrackerController {

  private final ProductNewAlertService productNewAlertService;
  private final ProductCheckPriceService productCheckPriceService;

  @PostMapping("/set-alert")
  public ResponseEntity<ProductAlert> setAlert(@RequestBody ProductNewAlert product)
      throws ProductNotFound {
    ProductAlert setNewAlert = productNewAlertService.setNewAlert(product);
    return ResponseEntity.ok().body(setNewAlert);
  }

  @PostMapping("/check-price")
  public ResponseEntity<String> checkPrice(@RequestBody ProductAlert productAlert)
      throws ProductNotFound {
    String checkProductPrice = productCheckPriceService.checkProductPrice(productAlert);
    return ResponseEntity.ok().body(checkProductPrice);
  }
}
