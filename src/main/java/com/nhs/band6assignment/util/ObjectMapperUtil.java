package com.nhs.band6assignment.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.band6assignment.model.Product;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ObjectMapperUtil {

  public List<Product> fetchProducts(String jsonFile) {

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      File file = new ClassPathResource(jsonFile).getFile();
      return objectMapper.readValue(file, new TypeReference<>() {});
    } catch (IOException e) {
      return Collections.emptyList();
    }
  }
}
