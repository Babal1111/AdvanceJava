package com.example.productApp.exceptions;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public String handleGlobalException(RuntimeException ex, Model model){
        model.addAttribute("errormessage", ex.getMessage());
        return "error-page";

    }

}
