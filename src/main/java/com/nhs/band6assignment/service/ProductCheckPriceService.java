package com.nhs.band6assignment.service;

import com.nhs.band6assignment.exception.ProductNotFound;
import com.nhs.band6assignment.model.Product;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.util.ObjectMapperUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCheckPriceService {

  public String checkProductPrice(ProductAlert productAlert) throws ProductNotFound {
    if (findProductWithDroppedPricesByUrl(productAlert.url()).isPresent()) {
      Product droppedPrices = findProductWithDroppedPricesByUrl(productAlert.url()).get();

      if (droppedPrices.currentPrice().compareTo(productAlert.expectedPrice()) < 0) {
        return productAlert.name() + " now has a new price of £" + droppedPrices.currentPrice();
      }
      return productAlert.name() + " has not dropped in price";
    }
    throw new ProductNotFound(productAlert.url());
  }

  private Optional<Product> findProductWithDroppedPricesByUrl(String url) {
    return new ObjectMapperUtil()
        .fetchProducts("productsDroppedPrices.json").stream()
            .filter(x -> x.url().equals(url))
            .findFirst();
  }
}
