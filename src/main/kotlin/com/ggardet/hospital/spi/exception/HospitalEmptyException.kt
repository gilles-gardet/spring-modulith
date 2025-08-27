package com.ggardet.hospital.spi.exception

import com.ggardet.core.spi.exception.InternalNotFoundException
import java.util.*

class HospitalEmptyException(hospitalId: UUID, type: String) : 
    InternalNotFoundException("Hospital $hospitalId does not have $type")