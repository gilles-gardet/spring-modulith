package com.ggardet.patient.spi.exception

import java.util.*

class MedicationNotFoundException(id: UUID?) : RuntimeException(
    "Medication with id $id not found"
)