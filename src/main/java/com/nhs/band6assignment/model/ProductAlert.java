package com.nhs.band6assignment.model;

import java.math.BigDecimal;

public record ProductAlert(
    Long id,
    String name,
    String url,
    BigDecimal expectedPrice,
    BigDecimal currentPrice,
    String message) {}
