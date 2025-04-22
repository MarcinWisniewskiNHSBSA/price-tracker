package com.nhs.band6assignment.model;

import java.math.BigDecimal;

public record Product(Long id, String url, String name, BigDecimal currentPrice) {}
