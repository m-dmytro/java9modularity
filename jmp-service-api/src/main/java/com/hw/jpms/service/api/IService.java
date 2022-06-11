package com.hw.jpms.service.api;

import com.hw.jpms.dto.BankCard;
import com.hw.jpms.dto.Subscription;
import com.hw.jpms.dto.User;

import java.util.List;
import java.util.Optional;

public interface IService {

  void subscribe(BankCard bankCard);
  Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);
  List<User> getAllUsers();

}
