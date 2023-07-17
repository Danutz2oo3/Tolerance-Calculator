package com.tolerance_calculator.tolerancecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ToleranceCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToleranceCalculatorApplication.class, args);
    }

}
