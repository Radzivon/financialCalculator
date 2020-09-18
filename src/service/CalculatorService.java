package service;

import enums.Operation;
import validator.NumberValidator;

import java.math.BigDecimal;

public class CalculatorService {

    public BigDecimal calculate(String strA, String strB, Operation operation) throws Exception {
        if (NumberValidator.isValidNumber(strA) && NumberValidator.isValidNumber(strB)) {
            BigDecimal result;
            BigDecimal a = new BigDecimal(strA);
            BigDecimal b = new BigDecimal(strB);
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


    public BigDecimal minus(String strA, String strB) throws Exception {
        if (NumberValidator.isValidNumber(strA) && NumberValidator.isValidNumber(strB)) {
            BigDecimal a = new BigDecimal(strA);
            BigDecimal b = new BigDecimal(strB);
            return a.subtract(b);
        }
        throw new Exception("Incorrect data");
    }
}
