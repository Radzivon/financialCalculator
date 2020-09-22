package service;

import enums.Operation;
import validator.NumberValidator;

import java.math.BigDecimal;

public class CalculatorService {

    public BigDecimal calculate(String strA, String strB, Operation operation) throws Exception {
        if (NumberValidator.isValidNumber(strA) && NumberValidator.isValidNumber(strB)) {

            String newStrA = replacingCommaWithPeriod(strA);
            String newStrB = replacingCommaWithPeriod(strB);

            BigDecimal result;
            BigDecimal a = new BigDecimal(newStrA);
            BigDecimal b = new BigDecimal(newStrB);
            switch (operation) {
                case PLUS:
                    result = a.add(b);
                    break;
                case MINUS:
                    result = a.subtract(b);
                    break;
                default:
                    throw new Exception("Operation exception");
            }
            return result;
        }
        throw new Exception("Incorrect data");
    }

    private String replacingCommaWithPeriod(String str) {
        return str.replace(",", ".");
    }
}
