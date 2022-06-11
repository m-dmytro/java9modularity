package com.hw.jpms.dto;

import com.hw.jpms.dto.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class BankCardTypeMapper {

  private final Map<BankCardType, Function<User, BankCard>> mappings;

  public BankCardTypeMapper() {
    mappings = Map.of(
        BankCardType.DEBIT, user -> new DebitBankCard(UUID.randomUUID().toString(), user),
        BankCardType.CREDIT, user -> new CreditBankCard(UUID.randomUUID().toString(), user)
    );
  }

  public BankCard fromBankCardType(BankCardType type, User user) {
    return Optional.ofNullable(mappings.get(type))
        .orElseThrow(() -> new IllegalArgumentException("Wrong BankCardType: " + type))
        .apply(user);
  }

}
