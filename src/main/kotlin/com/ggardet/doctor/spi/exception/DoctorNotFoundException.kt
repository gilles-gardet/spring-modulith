package com.ggardet.doctor.spi.exception

import com.ggardet.core.spi.exception.InternalNotFoundException
import java.util.*

class DoctorNotFoundException(doctorId: UUID) : InternalNotFoundException("Doctor not found: $doctorId")