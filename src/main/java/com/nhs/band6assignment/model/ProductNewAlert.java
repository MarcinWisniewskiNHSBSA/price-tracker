package com.nhs.band6assignment.model;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductNewAlert(String url, Frequency frequency, BigDecimal expectedPrice) {}
