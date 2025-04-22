package com.nhs.band6assignment.service;

import com.nhs.band6assignment.exception.ProductNotFound;
import com.nhs.band6assignment.mapper.ProductAlertMapper;
import com.nhs.band6assignment.model.Product;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.model.ProductNewAlert;
import com.nhs.band6assignment.util.ObjectMapperUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductNewAlertService {

  private final ProductAlertSenderService productAlertSenderService;

  public ProductAlert setNewAlert(ProductNewAlert newAlert) throws ProductNotFound {
    if (findProductById(newAlert).isPresent()) {
      Product currentProduct = findProductById(newAlert).get();

      boolean isAlertNeeded = newAlert.expectedPrice().compareTo(currentProduct.currentPrice()) < 0;

      if (isAlertNeeded) {
        ProductAlert productAlert =
            ProductAlertMapper.INSTANCE.mapProductAlert(newAlert, currentProduct);
        productAlertSenderService.scheduleAlert(
            newAlert.frequency().getCronExpression(), productAlert);
        return productAlert;
      }
      return ProductAlertMapper.INSTANCE.mapAlertNotNeeded(newAlert, currentProduct);
    }
    throw new ProductNotFound(newAlert.url());
  }

  private Optional<Product> findProductById(ProductNewAlert newAlert) {
    return new ObjectMapperUtil()
        .fetchProducts("products.json").stream()
            .filter(x -> x.url().equals(newAlert.url()))
            .findFirst();
  }
}
