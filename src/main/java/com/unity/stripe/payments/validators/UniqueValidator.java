package com.unity.stripe.payments.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.unity.stripe.payments.service.CustomerRegistrationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class UniqueValidator implements ConstraintValidator<UniqueEmail, String> {

//    private final String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Autowired
    CustomerRegistrationService customerService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(email)) return false;

//        Pattern pattern = Pattern.compile(regex);
//        var result = pattern.matcher(email).matches();

//        if(!result){
//             return false;
//        }

        if (customerService.isEmailExist(email)) {
            return false;
        } else {
            return true;
        }

    }

}
