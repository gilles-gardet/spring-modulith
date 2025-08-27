package com.ggardet.patient.spi.exception

import java.util.*

class PatientNotFoundException(id: UUID?) : RuntimeException(
    "Patient with id $id not found"
)