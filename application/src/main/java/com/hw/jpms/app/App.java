package com.hw.jpms.app;

import com.hw.jpms.api.IBank;
import com.hw.jpms.cloud.bank.service.api.BankImpl;
import com.hw.jpms.cloud.bank.service.impl.ServiceImpl;
import com.hw.jpms.dto.BankCard;
import com.hw.jpms.dto.BankCardType;
import com.hw.jpms.dto.Subscription;
import com.hw.jpms.dto.User;
import com.hw.jpms.service.api.IService;

import java.time.LocalDate;
import java.util.Optional;

public class App {

  public static void main(String[] args) {

    IBank bank = new BankImpl();
    IService service = new ServiceImpl();

    User requestedUser = new User("Tom", "Cruise", LocalDate.parse("1962-07-03"));
    BankCard creditCard = bank.createBankCard(requestedUser, BankCardType.CREDIT);

    service.subscribe(creditCard);

    boolean isUserPresentInMemory = service.getAllUsers().stream().anyMatch(storedUser -> storedUser.equals(requestedUser));
    Optional<Subscription> userSubscription = service.getSubscriptionByBankCardNumber(creditCard.getNumber());

    if (isUserPresentInMemory) {
      System.out.printf("User %s is served by the bank. Subscription data: %s%n",
          requestedUser.toString(),
          userSubscription.isPresent() ? userSubscription.get().toString() : "not subscribed");
    } else {
      System.out.printf("User %s is not served by the bank.%n", requestedUser.toString());
    }

  }


}
