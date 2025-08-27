package com.ggardet.doctor

import com.ggardet.core.HospitalDeletedEvent
import org.slf4j.LoggerFactory
import org.springframework.modulith.events.ApplicationModuleListener
import org.springframework.stereotype.Service

@Service
internal class HospitalEventManager(
    private val doctorServiceInterface: DoctorServiceInterface
) {
    private val log = LoggerFactory.getLogger(HospitalEventManager::class.java)

    @ApplicationModuleListener
    fun onRemovedHospitalEvent(event: HospitalDeletedEvent) {
        val hospitalId = event.hospitalId
        log.info("Received removal of hospital {}. Need to unlink doctors", hospitalId)
        val doctors = doctorServiceInterface.findAllByHospital(hospitalId)
        doctors.forEach { doctor -> doctorServiceInterface.unlinkDoctorFromHospital(doctor) }
        log.info("Finished unlinking all doctors from hospital {}.", hospitalId)
    }
}