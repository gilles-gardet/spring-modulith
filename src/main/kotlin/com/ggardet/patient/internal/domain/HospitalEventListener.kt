package com.ggardet.patient.internal.domain

import com.ggardet.core.HospitalDeletedEvent
import com.ggardet.patient.internal.repository.PatientRepository
import org.slf4j.LoggerFactory
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Component

@Component
class HospitalEventListener(
    private val patientRepository: PatientRepository
) {
    private val log = LoggerFactory.getLogger(HospitalEventListener::class.java)

    @ApplicationModuleListener
    fun on(event: HospitalDeletedEvent) {
        log.info("Handling hospital deletion event for hospital: {}", event.hospitalId)
        val patients = patientRepository.findByHospitalId(event.hospitalId)
        if (patients.isNotEmpty()) {
            log.info("Updating {} patients for hospital: {}", patients.size, event.hospitalId)
            patients.forEach { patient ->
                patient.hospitalId = null
                patient.serviceId = null
            }
            patientRepository.saveAll(patients)
        }
    }
}