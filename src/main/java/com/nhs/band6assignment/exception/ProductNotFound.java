package com.nhs.band6assignment.exception;

import lombok.Getter;

@Getter
public class ProductNotFound extends Exception {

  private final String url;

  public ProductNotFound(String url) {
    super(String.format("Product with the following URL %s not found", url));
    this.url = url;
  }
}
