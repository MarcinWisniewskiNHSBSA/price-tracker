package com.nhs.band6assignment.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Frequency {
  // morning set to 10 seconds for testing purposes
  MORNING("0/10 * * * * *"),
  NOON("0 0 12 * * ?"),
  MIDNIGHT("0 0 0 * * ?");

  public final String cronExpression;
}
