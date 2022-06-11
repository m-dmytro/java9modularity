package com.hw.jpms.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

public class Subscription {

  private final String bankcard;
  private final LocalDate startDate;

  public Subscription(String bankcard, LocalDate startDate) {
    this.bankcard = bankcard;
    this.startDate = startDate;
  }

  public String getBankcard() {
    return bankcard;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    Subscription that = (Subscription) o;

    return new EqualsBuilder().append(bankcard, that.bankcard).append(startDate, that.startDate).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(bankcard).append(startDate).toHashCode();
  }

  @Override
  public String toString() {
    return "Subscription{" +
        "bankcard='" + bankcard + '\'' +
        ", startDate=" + startDate +
        '}';
  }

}
