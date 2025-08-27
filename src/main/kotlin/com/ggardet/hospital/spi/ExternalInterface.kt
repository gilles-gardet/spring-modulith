package com.ggardet.hospital.spi

import java.util.*

interface ExternalInterface {
    fun publishDeleteHospitalEvent(hospitalId: UUID)
}