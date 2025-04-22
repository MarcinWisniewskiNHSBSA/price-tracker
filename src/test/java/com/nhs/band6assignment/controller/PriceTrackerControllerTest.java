package com.nhs.band6assignment.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.band6assignment.model.Frequency;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.model.ProductNewAlert;
import com.nhs.band6assignment.service.ProductCheckPriceService;
import com.nhs.band6assignment.service.ProductNewAlertService;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PriceTrackerController.class)
@DisplayName("Price Tracker Controller Tests")
class PriceTrackerControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ProductNewAlertService productNewAlertService;

  @MockitoBean
  private ProductCheckPriceService productCheckPriceService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("setNewAlert() test")
  void testSetAlert() throws Exception {
    ProductNewAlert productNewAlert = new ProductNewAlert("https://example.com/product/1",
        Frequency.MORNING, BigDecimal.valueOf(150.00));

    ProductAlert productAlert = new ProductAlert(1L, "Product 1", "https://example.com/product/1",
        BigDecimal.valueOf(150.00), BigDecimal.valueOf(200.00),
        "A new alert for Product 1 has been added.");

    when(productNewAlertService.setNewAlert(productNewAlert)).thenReturn(productAlert);

    mockMvc.perform(post("/track/set-alert").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productNewAlert))).andExpect(status().isOk());
  }

  @Test
  @DisplayName("testCheckPrice() test")
  void testCheckPrice() throws Exception {
    ProductAlert productAlert = new ProductAlert(1L, "Product 1", "https://example.com/product/1",
        BigDecimal.valueOf(150.00), BigDecimal.valueOf(200.00),
        "A new alert for Product 1 has been added.");
    String responseMessage = "The current price for Product 1 is £200.00";

    when(productCheckPriceService.checkProductPrice(productAlert)).thenReturn(responseMessage);

    mockMvc.perform(post("/track/check-price").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(productAlert))).andExpect(status().isOk());
  }
}