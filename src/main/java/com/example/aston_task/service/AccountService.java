package com.example.aston_task.service;

import com.example.aston_task.exception.IncorrectPinException;
import com.example.aston_task.exception.NoSuchAccountNumber;
import com.example.aston_task.model.BankAccount;
import com.example.aston_task.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final BankAccountRepository bankAccountRepository;


    public AccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount addBankAccount(BankAccount bankAccount) {
        String pin = bankAccount.getPinCode();
        if (pin.length() != 4 || !pin.matches("[-+]?\\d+")) {
            throw new IncorrectPinException();
        } else {
            return bankAccountRepository.save(bankAccount);
        }
    }

    public BankAccount deleteBankAccount(Long accountNumber, String pin) {
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber).orElseThrow(NoSuchAccountNumber::new);
        if(!bankAccount.getPinCode().equals(pin)) {
            throw new IncorrectPinException();
        } else {
            bankAccountRepository.deleteById(bankAccount.getAccountNumber());
        }
        return bankAccount;
    }

    public List<BankAccount> getAllBankAccounts() {
        return new ArrayList<>(bankAccountRepository.findAll());
    }
}
