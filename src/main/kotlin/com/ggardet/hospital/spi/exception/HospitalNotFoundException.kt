package com.ggardet.hospital.spi.exception

import com.ggardet.core.spi.exception.InternalNotFoundException
import java.util.*

class HospitalNotFoundException(hospitalId: UUID) : 
    InternalNotFoundException("Hospital not found: $hospitalId")