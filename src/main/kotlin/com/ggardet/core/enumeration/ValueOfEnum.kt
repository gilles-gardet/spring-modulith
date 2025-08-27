package com.ggardet.core.enumeration

import jakarta.validation.Constraint
import jakarta.validation.Payload
import org.springframework.modulith.NamedInterface
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import kotlin.reflect.KClass

@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER, FIELD, ANNOTATION_CLASS, CONSTRUCTOR, VALUE_PARAMETER, TYPE)
@Retention(RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [ValueOfEnumValidator::class])
@NamedInterface("core-value-of-enum-spi")
annotation class ValueOfEnum(
    val enumClass: KClass<out Enum<*>>,
    val regexp: String,
    val field: String,
    val message: String = "The field {field} must equals one of these values : {regexp}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)