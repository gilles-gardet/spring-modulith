package com.ggardet.core.enumeration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import static java.util.stream.Stream.of;

class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        return acceptedValues.contains(value.toString().toUpperCase(Locale.ROOT));
    }
}
