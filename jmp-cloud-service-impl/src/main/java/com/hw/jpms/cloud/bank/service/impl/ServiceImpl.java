package com.hw.jpms.cloud.bank.service.impl;

import com.hw.jpms.dto.BankCard;
import com.hw.jpms.dto.Subscription;
import com.hw.jpms.dto.User;
import com.hw.jpms.service.api.IService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceImpl implements IService {

  private final Map<User, List<BankCard>> storage = new ConcurrentHashMap<>();
  private final List<Subscription> subscriptions = new CopyOnWriteArrayList<>();

  @Override
  public void subscribe(BankCard bankCard) {
    storage.computeIfAbsent(bankCard.getUser(), value -> new ArrayList<>()).add(bankCard);
    subscriptions.add(new Subscription(bankCard.getNumber(), LocalDate.now()));
  }

  @Override
  public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
    return subscriptions.stream()
        .filter(subscription -> subscription.getBankcard().equals(cardNumber))
        .findFirst();
  }

  @Override
  public List<User> getAllUsers() {
    return new ArrayList<>(storage.keySet());
  }

}
