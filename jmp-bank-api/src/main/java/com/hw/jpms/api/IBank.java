package com.hw.jpms.api;

import com.hw.jpms.dto.BankCard;
import com.hw.jpms.dto.BankCardType;
import com.hw.jpms.dto.User;

public interface IBank {

  BankCard createBankCard(User user, BankCardType cardType);

}
