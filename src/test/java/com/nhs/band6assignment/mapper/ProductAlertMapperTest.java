package com.nhs.band6assignment.mapper;


import static org.assertj.core.api.Assertions.assertThat;

import com.nhs.band6assignment.model.Frequency;
import com.nhs.band6assignment.model.Product;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.model.ProductNewAlert;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

@DisplayName("Product Alert Mapper Tests")
class ProductAlertMapperTest {

  private final ProductAlertMapper mapper =
      Mappers.getMapper(ProductAlertMapper.class);

  @Test
  @DisplayName("mapProductAlert() test")
  void shouldMapProductAlert() {
    // Given
    ProductNewAlert productNewAlert = new ProductNewAlert("https://example.com/product/1",
        Frequency.MORNING, BigDecimal.valueOf(150.00));

    Product product = new Product(1L, "https://example.com/product/1", "Product 1",
        BigDecimal.valueOf(200.00));
    // When
    ProductAlert map = mapper.mapProductAlert(productNewAlert, product);
    // Then
    assertThat(map.url()).isEqualTo("https://example.com/product/1");
    assertThat(map.message()).startsWith("A new alert for");
  }

  @Test
  @DisplayName("mapAlertNotNeeded() test")
  void shouldMapAlertNotNeeded() {
    // Given
    ProductNewAlert productNewAlert = new ProductNewAlert("https://example.com/product/1",
        Frequency.MORNING, BigDecimal.valueOf(150.00));

    Product product = new Product(1L, "https://example.com/product/1", "Product 1",
        BigDecimal.valueOf(200.00));
    // When
    ProductAlert map = mapper.mapAlertNotNeeded(productNewAlert, product);
    // Then
    assertThat(map.url()).isEqualTo("https://example.com/product/1");
    assertThat(map.message()).startsWith("Your alert has not been created");
  }
}