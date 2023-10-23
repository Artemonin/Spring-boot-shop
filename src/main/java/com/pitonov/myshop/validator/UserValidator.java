package com.pitonov.myshop.validator;

import com.pitonov.myshop.entity.User;
import com.pitonov.myshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.not_empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.not_empty");

        if(user.getUsername().length()<4){
            errors.rejectValue("username", "register.error.username.less_4");
        }

        if(user.getUsername().length()>32){
            errors.rejectValue("username", "register.error.username.over_32");
        }

        if(userService.findByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "register.error.email.duplicated");
        }

        if(user.getPassword().length()<6){
            errors.rejectValue("password", "register.error.password.less_8");
        }

        if(user.getPassword().length()>16){
            errors.rejectValue("password", "register.error.password.over_16");
        }

        if(user.getAge() < 12){
            errors.rejectValue("age", "register.error.age.less_12");
        }

    }
}
