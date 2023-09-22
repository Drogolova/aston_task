package com.example.aston_task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Задан некорректный пин-код")
public class IncorrectPinException extends RuntimeException{
}
