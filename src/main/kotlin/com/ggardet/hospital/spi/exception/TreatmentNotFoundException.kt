package com.ggardet.hospital.spi.exception

import com.ggardet.core.spi.exception.InternalNotFoundException
import java.util.*

class TreatmentNotFoundException(hospitalId: UUID, treatmentId: UUID) : 
    InternalNotFoundException("Treatment $treatmentId of hospital $hospitalId not found")