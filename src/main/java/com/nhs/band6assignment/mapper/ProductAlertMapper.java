package com.nhs.band6assignment.mapper;

import com.nhs.band6assignment.model.Product;
import com.nhs.band6assignment.model.ProductAlert;
import com.nhs.band6assignment.model.ProductNewAlert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class ProductAlertMapper {

  public static final ProductAlertMapper INSTANCE = Mappers.getMapper(ProductAlertMapper.class);

  public ProductAlert mapProductAlert(ProductNewAlert productNewAlert, Product product) {
    return new ProductAlert(
        new IdGeneratorUtil().getNextLong(),
        product.name(),
        product.url(),
        productNewAlert.expectedPrice(),
        product.currentPrice(),
        "A new alert for "
            + product.name()
            + " has been added. You will be notified when the current price £"
            + product.currentPrice()
            + " will drop to £"
            + productNewAlert.expectedPrice());
  }

  public ProductAlert mapAlertNotNeeded(ProductNewAlert productNewAlert, Product product) {
    return new ProductAlert(
        new IdGeneratorUtil().getNextLong(),
        product.name(),
        product.url(),
        productNewAlert.expectedPrice(),
        product.currentPrice(),
        "Your alert has not been created as the expected price £"
            + productNewAlert.expectedPrice()
            + " is higher or equal than the current price £"
            + product.currentPrice());
  }

  // id generator only for the purpose of this task
  public static class IdGeneratorUtil {
    private static Long id = 0L;

    public Long getNextLong() {
      return ++id;
    }
  }
}
