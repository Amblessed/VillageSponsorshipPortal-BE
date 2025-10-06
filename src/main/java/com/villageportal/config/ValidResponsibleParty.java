package com.villageportal.config;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ResponsiblePartyValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidResponsibleParty {
    String message() default "A pupil must have either a parent or a guardian, not both or neither.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
