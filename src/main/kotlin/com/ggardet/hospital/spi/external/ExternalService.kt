package com.ggardet.hospital.spi.external

import com.ggardet.hospital.spi.ExternalInterface
import com.ggardet.core.HospitalDeletedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import java.util.*

@Component
internal class ExternalService(
    private val eventPublisher: ApplicationEventPublisher
) : ExternalInterface {

    override fun publishDeleteHospitalEvent(hospitalId: UUID) {
        eventPublisher.publishEvent(HospitalDeletedEvent(hospitalId))
    }
}