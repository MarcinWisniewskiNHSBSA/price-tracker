package com.nhs.band6assignment.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhs.band6assignment.exception.ProductNotFound;
import com.nhs.band6assignment.model.Product;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.util.ObjectMapperUtil;
import java.math.BigDecimal;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Product Check Price Service Tests")
class ProductCheckPriceServiceTest {

  private ProductCheckPriceService productCheckPriceService;
  private ObjectMapperUtil objectMapperUtil;

  @BeforeEach
  void setUp() {
    objectMapperUtil = mock(ObjectMapperUtil.class);
    productCheckPriceService = new ProductCheckPriceService();
  }

  @Test
  @DisplayName("Should return message when price has dropped")
  @SneakyThrows
  void shouldReturnMessageWhenPriceDropped() {
    // Given
    ProductAlert productAlert = new ProductAlert(1L, "Product 1", "https://example.com/product/1",
        BigDecimal.valueOf(150.00), BigDecimal.valueOf(200.00),
        "A new alert for Product 1 has been added.");
    Product product = new Product(1L, "Product 1", "https://example.com/product/1",
        BigDecimal.valueOf(100.00));
    when(objectMapperUtil.fetchProducts("productsDroppedPrices.json")).thenReturn(List.of(product));
    // When
    String result = productCheckPriceService.checkProductPrice(productAlert);
    // Then
    assertThat(result).isEqualTo("Product 1 now has a new price of £100.00");
  }

  @Test
  @DisplayName("Should return message when price has not dropped")
  @SneakyThrows
  void shouldReturnMessageWhenPriceNotDropped() {
    // Given
    ProductAlert productAlert = new ProductAlert(1L, "Product 1", "https://example.com/product/3",
        BigDecimal.valueOf(150.00), BigDecimal.valueOf(300.00),
        "A new alert for Product 1 has been added.");
    Product product = new Product(1L, "Product 1", "https://example.com/product/3",
        BigDecimal.valueOf(300.00));
    when(objectMapperUtil.fetchProducts("productsDroppedPrices.json")).thenReturn(List.of(product));

    // When
    String result = productCheckPriceService.checkProductPrice(productAlert);

    // Then
    assertThat(result).isEqualTo("Product 1 has not dropped in price");
  }

  @Test
  @DisplayName("Should throw the ProductNotFound exception when product not found")
  void shouldThrowProductNotFound() {
    // Given
    ProductAlert productAlert = new ProductAlert(1L, "Product 1", "https://example.com/product/",
        BigDecimal.valueOf(150.00), BigDecimal.valueOf(200.00),
        "A new alert for Product 1 has been added.");
    when(objectMapperUtil.fetchProducts("productsDroppedPrices.json")).thenReturn(emptyList());

    // When & Then
    assertThrows(ProductNotFound.class,
        () -> productCheckPriceService.checkProductPrice(productAlert));
  }
}