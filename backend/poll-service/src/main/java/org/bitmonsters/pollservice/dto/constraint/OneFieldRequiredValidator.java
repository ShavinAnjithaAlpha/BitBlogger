package org.bitmonsters.pollservice.dto.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bitmonsters.pollservice.dto.NewPollAnswerDto;

public class OneFieldRequiredValidator implements ConstraintValidator<OneFieldRequired, NewPollAnswerDto> {

    @Override
    public void initialize(OneFieldRequired constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(NewPollAnswerDto dto, ConstraintValidatorContext context) {

        boolean isFistFieldProvided = dto.answers() != null && !dto.answers().isEmpty();
        boolean isSecondFieldProvided = dto.optionalAnswer() != null && !dto.optionalAnswer().trim().isEmpty();

        return isFistFieldProvided != isSecondFieldProvided;
    }
}
