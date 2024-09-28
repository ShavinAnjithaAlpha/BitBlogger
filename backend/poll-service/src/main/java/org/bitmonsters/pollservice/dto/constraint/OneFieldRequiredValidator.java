package org.bitmonsters.pollservice.dto.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bitmonsters.pollservice.dto.NewPollAnswerDto;

public class OneFieldRequiredValidator implements ConstraintValidator<OneFieldRequired, NewPollAnswerDto.NewPollAnswerRecord> {

    @Override
    public void initialize(OneFieldRequired constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(NewPollAnswerDto.NewPollAnswerRecord dto, ConstraintValidatorContext context) {

        boolean isFistFieldProvided = dto.answerId() != null && dto.answerId() != 0;
        boolean isSecondFieldProvided = dto.answer() != null && !dto.answer().trim().isEmpty();

        return isFistFieldProvided != isSecondFieldProvided;
    }
}
