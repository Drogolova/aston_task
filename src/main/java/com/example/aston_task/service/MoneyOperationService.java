package com.example.aston_task.service;

import com.example.aston_task.exception.IncorrectPinException;
import com.example.aston_task.exception.NoSuchAccountNumber;
import com.example.aston_task.exception.WithdrawalOverBalanceException;
import com.example.aston_task.model.BankAccount;
import com.example.aston_task.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MoneyOperationService {
    private final BankAccountRepository bankAccountRepository;

    public MoneyOperationService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BigDecimal deposit(Long accountNumber, String pin, BigDecimal deposit) {
        if (!isPinAndAccountNumberCorrect(accountNumber, pin)) {
            throw new IncorrectPinException();
        } else {
            BankAccount account = bankAccountRepository.findById(accountNumber).orElseThrow();
            account.setBalance(account.getBalance().add(deposit));
            return bankAccountRepository.findById(accountNumber).orElseThrow().getBalance();
        }
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BigDecimal withdraw(Long accountNumber, String pin, BigDecimal withdrawal) {
        BankAccount account = bankAccountRepository.findById(accountNumber).orElseThrow(NoSuchAccountNumber::new);
        if (!isPinAndAccountNumberCorrect(accountNumber, pin)) {
            throw new IncorrectPinException();
        } else  if (account.getBalance().compareTo(withdrawal) < 0) {
            throw new WithdrawalOverBalanceException();
        } else {
            account.setBalance(account.getBalance().subtract(withdrawal));
            return bankAccountRepository.findById(accountNumber).orElseThrow().getBalance();
        }
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public BigDecimal transfer(Long senderAccountNumber, String senderPin, BigDecimal transferMoney, Long recipientAccountNumber) {
        BankAccount senderAccount = bankAccountRepository.findById(senderAccountNumber).orElseThrow(NoSuchAccountNumber::new);
        BankAccount recipientAccount = bankAccountRepository.findById(recipientAccountNumber).orElseThrow(NoSuchAccountNumber::new);
        if (!isPinAndAccountNumberCorrect(senderAccountNumber, senderPin)) {
            throw new IncorrectPinException();
        } else  if (senderAccount.getBalance().compareTo(transferMoney) < 0) {
            throw new WithdrawalOverBalanceException();
        } else if(!isAccountNumberCorrect(recipientAccountNumber)) {
            throw new NoSuchAccountNumber();
        } else {
            senderAccount.setBalance(senderAccount.getBalance().subtract(transferMoney));
            recipientAccount.setBalance(recipientAccount.getBalance().add(transferMoney));
            return bankAccountRepository.findById(senderAccountNumber).orElseThrow().getBalance();}
        }


    public boolean isPinAndAccountNumberCorrect(Long accountNumber, String pin) {
       return bankAccountRepository.findById(accountNumber).get().getPinCode().equals(pin);
    }

    public boolean isAccountNumberCorrect(Long accountNumber) {
        return bankAccountRepository.findById(accountNumber).isPresent();
    }
}
