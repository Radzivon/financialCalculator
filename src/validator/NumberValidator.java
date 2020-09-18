package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {
    private static final String regex = "^[-+]?[0-9]*([.,][0-9]+)?$";
    public static boolean isValidNumber(String str){
        Pattern pattern = Pattern.compile(regex);

        if(str == null){
            return false;
        }

        Matcher matcher = pattern.matcher(str);

        return  matcher.matches();
    }
}
