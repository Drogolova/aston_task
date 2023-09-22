package com.example.aston_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Сумма снятия превышает баланс")
public class WithdrawalOverBalanceException extends RuntimeException {
}
