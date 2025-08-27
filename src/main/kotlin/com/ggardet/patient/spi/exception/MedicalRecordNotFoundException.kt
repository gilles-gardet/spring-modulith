package com.ggardet.patient.spi.exception

import java.util.*

class MedicalRecordNotFoundException(id: UUID?) : RuntimeException(
    "Medical record with id $id not found"
)