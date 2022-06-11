package com.hw.jpms.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class BankCard {

  private final String number;
  private final User user;

  public BankCard(String number, User user) {
    this.number = number;
    this.user = user;
  }

  public String getNumber() {
    return number;
  }

  public User getUser() {
    return user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (o == null || getClass() != o.getClass()) return false;

    BankCard bankCard = (BankCard) o;

    return new EqualsBuilder().append(number, bankCard.number).append(user, bankCard.user).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(number).append(user).toHashCode();
  }

  @Override
  public String toString() {
    return "BankCard{" +
        "number='" + number + '\'' +
        ", user=" + user +
        '}';
  }
}
