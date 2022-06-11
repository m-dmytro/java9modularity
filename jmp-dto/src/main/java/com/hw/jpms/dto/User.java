package com.hw.jpms.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

public class User {

  private final String name;
  private final String surname;
  private final LocalDate birthday;

  public User(String name, String surname, LocalDate birthday) {
    this.name = name;
    this.surname = surname;
    this.birthday = birthday;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return new EqualsBuilder().append(name, user.name).append(surname, user.surname).append(birthday, user.birthday).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(name).append(surname).append(birthday).toHashCode();
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", surname='" + surname + '\'' +
        ", birthday=" + birthday +
        '}';
  }

}
