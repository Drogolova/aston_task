package com.example.aston_task.controller;

import com.example.aston_task.model.BankAccount;
import com.example.aston_task.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/add")
    public ResponseEntity<BankAccount> addAccount(@RequestBody BankAccount bankAccount) {
        BankAccount savedBankAccount = accountService.addBankAccount(bankAccount);
        return ResponseEntity.ok(savedBankAccount);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BankAccount> deleteAccount(@RequestParam Long accountNumber, @RequestParam String pin) {
        return ResponseEntity.ok(accountService.deleteBankAccount(accountNumber, pin));
    }

    @GetMapping("/getAllAccounts")
    public ResponseEntity<List<BankAccount>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllBankAccounts());
    }
}
