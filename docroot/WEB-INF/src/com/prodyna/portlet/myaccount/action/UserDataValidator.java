package com.prodyna.portlet.myaccount.action;

import java.util.Calendar;
import java.util.List;

import com.liferay.portal.kernel.util.Validator;
import com.prodyna.model.UserData;

public class UserDataValidator {
    public static boolean validateUserData(UserData userData, List errors) {
        boolean valid = true;

        Calendar c = Calendar.getInstance();
        c.set(1891, 0, 1);
        
        
        if (Validator.isNull(userData.getT_ShirtSize())) {
            errors.add("t-shirt-size-required");
            valid = false;
        }
        if (Validator.isNull(userData.getDateOfBirth())) {
        	errors.add("date-of-birth-required");
        	valid = false;
        } else if( userData.getDateOfBirth().before(c.getTime()) ) {
        	errors.add("valid-date-of-birth-required");
        	errors.add("dob-must-be-after-1891");
        	valid = false;
        }
        if (Validator.isNull(userData.getGlasses())) {
        	errors.add("glasses-required");
        	valid = false;
        }
        if (Validator.isNull(userData.getCoffeesPerDay()) || userData.getCoffeesPerDay() < 0 || userData.getCoffeesPerDay() > 21 ) {
        	errors.add("coffees-per-day-required");
        	valid = false;
        }
        if (Validator.isNull(userData.getHeightCm()) || userData.getHeightCm() < 53 || userData.getHeightCm() > 273 ) {
        	errors.add("height-cm-required");
        	valid = false;
        }
        return valid;
    }
}
