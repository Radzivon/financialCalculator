package service;

import enums.Operation;
import enums.RoundingType;
import validator.NumberValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorService {
    private static final int SCALE = 6;

    public BigDecimal round(String str, RoundingType roundingType) throws Exception {
        str = str.trim();
        if (NumberValidator.isValidNumber(str)) {
            String replacingCommaWithPeriod = replacingCommaWithPeriod(str);
            BigDecimal a = new BigDecimal(replacingCommaWithPeriod);
            BigDecimal result;
            switch (roundingType) {
                case MATHEMATICAL:
                    result = a.setScale(0, RoundingMode.HALF_UP);
                    break;
                case ACCOUNTING:
                    result = a.setScale(0, RoundingMode.HALF_EVEN);
                    break;
                case TRUNCATION:
                    result = a.setScale(0, RoundingMode.DOWN);
                    break;
                default:
                    throw new Exception("Operation exception");
            }
            return result;
        }
        throw new Exception("Incorrect data");
    }

    public BigDecimal calculate(String strA, String strB, String strC, String strD, Operation operationFirst,
                                Operation operationSecond, Operation operationThird) throws Exception {

        if (NumberValidator.isValidNumber(strA) && NumberValidator.isValidNumber(strB) &&
                NumberValidator.isValidNumber(strC) && NumberValidator.isValidNumber(strD)) {

            String newStrA = replacingCommaWithPeriod(strA);
            String newStrB = replacingCommaWithPeriod(strB);
            String newStrC = replacingCommaWithPeriod(strC);
            String newStrD = replacingCommaWithPeriod(strD);

            BigDecimal result;
            BigDecimal a = new BigDecimal(newStrA);
            BigDecimal b = new BigDecimal(newStrB);
            BigDecimal c = new BigDecimal(newStrC);
            BigDecimal d = new BigDecimal(newStrD);

            result = calculate(b, c, operationSecond);
            result = result.setScale(10);
            if (operationFirst.getPriority() > operationThird.getPriority()) {
                result = calculate(a, result, operationFirst);
                result = result.setScale(10);
                result = calculate(result, d, operationThird);
                result = result.setScale(10);
            } else {
                result = calculate(result, d, operationThird);
                result = result.setScale(10);
                result = calculate(a, result, operationFirst);
                result = result.setScale(10);
            }
            return result;
        }
        throw new Exception("Incorrect data");
    }

    private BigDecimal calculate(BigDecimal a, BigDecimal b, Operation operation) throws Exception {
        BigDecimal result;
        switch (operation) {
            case PLUS:
                result = a.add(b);
                break;
            case MINUS:
                result = a.subtract(b);
                break;
            case MULTIPLICATION:
                result = a.multiply(b);
                break;
            case DIVISION:
                if (b.equals(new BigDecimal("0"))) {
                    throw new Exception("You can't divide by zero");
                }
                result = a.divide(b, SCALE);
                break;
            default:
                throw new Exception("Operation exception");
        }
        return result;
    }

    private String replacingCommaWithPeriod(String str) {
        return str.replace(",", ".");
    }
}
