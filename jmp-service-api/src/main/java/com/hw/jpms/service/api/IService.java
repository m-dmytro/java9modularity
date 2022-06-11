package com.hw.jpms.service.api;

import com.hw.jpms.dto.BankCard;
import com.hw.jpms.dto.Subscription;
import com.hw.jpms.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface IService {

  void subscribe(BankCard bankCard);

  Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

  List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> filterCondition);

  List<User> getAllUsers();

  default double getAverageUsersAge() {
    return getAllUsers().stream()
        .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
        .average()
        .orElse(0.0);
  }

  static boolean isPayableUser(User user) {
    long userAge = ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now());
    return userAge >= 18;
  }

}
