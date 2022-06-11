package com.hw.jpms.app;

import com.hw.jpms.cloud.bank.service.api.BankImpl;
import com.hw.jpms.cloud.bank.service.impl.ServiceImpl;
import com.hw.jpms.cloud.bank.service.impl.SubscribeException;
import com.hw.jpms.dto.BankCardType;
import com.hw.jpms.dto.Subscription;
import com.hw.jpms.dto.User;
import com.hw.jpms.service.api.IService;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class App {

  public static void main(String[] args) {
    var bank = new BankImpl();
    var service = new ServiceImpl();

    var requestedUser1 = new User("Tom", "Cruise", LocalDate.parse("1962-07-03"));
    var creditCard1 = bank.createBankCard(requestedUser1, BankCardType.CREDIT);
    service.subscribe(creditCard1);

    var requestedUser2 = new User("Eddie", "Murphy", LocalDate.parse("1961-04-03"));
    var creditCard2 = bank.createBankCard(requestedUser2, BankCardType.CREDIT);
    service.subscribe(creditCard2);

    /* check user availability */
    boolean isUserPresentInMemory = service.getAllUsers().stream().anyMatch(storedUser -> storedUser.equals(requestedUser1));
    Subscription userSubscription = service.getSubscriptionByBankCardNumber(creditCard1.getNumber())
        .orElseThrow(() -> new SubscribeException("Not found subscription for ccard: " + creditCard1.getNumber()));

    if (isUserPresentInMemory) {
      System.out.printf("User %s is served by the bank. Subscription data: %s\n", requestedUser1, userSubscription.toString());
    } else {
      System.out.printf("User %s is not served by the bank.\n", requestedUser1);
    }

    /* check users' average age */
    System.out.printf("Users' average age %s\n", service.getAverageUsersAge());

    /* check is user over 18 years old */
    System.out.printf("User %s is user over 18 years old: %s\n", requestedUser1, IService.isPayableUser(requestedUser1));

    /* check SubscribeException */
    try {
      service.getSubscriptionByBankCardNumber("some_random_number")
          .orElseThrow(() -> new SubscribeException("Not found subscription for ccard: " + creditCard1.getNumber()));
    } catch (SubscribeException ex) {
      System.out.printf("SubscribeException is working fine\n");
    }

    /* check Predicate*/
    Predicate<Subscription> conditionInvalidSubscr = subscription -> subscription.getEndDate().isBefore(LocalDate.now().plusMonths(2));
    List<Subscription> invalidSubscriptions = service.getAllSubscriptionsByCondition(conditionInvalidSubscr);
    System.out.printf("All invalid subscriptions: " + invalidSubscriptions);
    Predicate<Subscription> conditionValidSubscr = subscription -> subscription.getEndDate().isAfter(LocalDate.now().plusMonths(2));
    List<Subscription> validSubscriptions = service.getAllSubscriptionsByCondition(conditionValidSubscr);
    System.out.printf("\nAll valid subscriptions: " + validSubscriptions);
  }

}
