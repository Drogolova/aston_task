package com.example.aston_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Аккаунт с указанным номером не найден")
public class NoSuchAccountNumber extends RuntimeException{
}
