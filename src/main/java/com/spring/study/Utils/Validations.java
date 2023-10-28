package com.spring.study.Utils;

import org.springframework.stereotype.Component;

@Component
public class Validations {
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
