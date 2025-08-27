package com.ggardet.core.enumeration

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.*

internal class ValueOfEnumValidator : ConstraintValidator<ValueOfEnum, CharSequence> {
    private lateinit var acceptedValues: List<String>

    override fun initialize(annotation: ValueOfEnum) {
        acceptedValues = annotation.enumClass.java.enumConstants
            .map { it.name }
            .toList()
    }

    override fun isValid(value: CharSequence?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) {
            return true
        }
        return acceptedValues.contains(value.toString().uppercase(Locale.ROOT))
    }
}