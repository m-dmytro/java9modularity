package com.hw.jpms.cloud.bank.service.api;

import com.hw.jpms.api.IBank;
import com.hw.jpms.dto.BankCard;
import com.hw.jpms.dto.BankCardType;
import com.hw.jpms.dto.BankCardTypeMapper;
import com.hw.jpms.dto.User;

public class BankImpl implements IBank {

  @Override
  public BankCard createBankCard(User user, BankCardType cardType) {
    return new BankCardTypeMapper().fromBankCardType(cardType, user);
  }

}
