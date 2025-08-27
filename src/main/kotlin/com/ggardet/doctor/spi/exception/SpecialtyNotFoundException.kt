package com.ggardet.doctor.spi.exception

import com.ggardet.core.spi.exception.InternalNotFoundException
import java.util.*

class SpecialtyNotFoundException : InternalNotFoundException {
    constructor(specialtyId: UUID) : super("Specialty not found: $specialtyId")
    constructor(specialtyName: String) : super("Specialty not found: $specialtyName")
}