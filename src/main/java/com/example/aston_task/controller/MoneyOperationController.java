package com.example.aston_task.controller;

import com.example.aston_task.service.MoneyOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/moneyOperation")
public class MoneyOperationController {

    private final MoneyOperationService moneyOperationService;

    public MoneyOperationController(MoneyOperationService moneyOperationService) {
        this.moneyOperationService = moneyOperationService;
    }

    @PutMapping("/deposit")
    public ResponseEntity<BigDecimal> deposit(@RequestParam Long accountNumber, @RequestParam String pin, @RequestParam BigDecimal deposit) {
        return ResponseEntity.ok(moneyOperationService.deposit(accountNumber, pin, deposit)) ;
    }

    @PutMapping("/withdraw")
    public ResponseEntity<BigDecimal> withdraw(@RequestParam Long accountNumber, @RequestParam String pin, @RequestParam BigDecimal withdrawal) {
        return ResponseEntity.ok(moneyOperationService.withdraw(accountNumber, pin, withdrawal)) ;
    }

    @PutMapping("/transfer")
    public ResponseEntity<BigDecimal> transfer(@RequestParam Long senderAccountNumber, @RequestParam String senderPin,
                                               @RequestParam BigDecimal transferMoney, @RequestParam Long recipientAccountNumber) {
        return ResponseEntity.ok(moneyOperationService.transfer(senderAccountNumber, senderPin, transferMoney, recipientAccountNumber));
    }
}
