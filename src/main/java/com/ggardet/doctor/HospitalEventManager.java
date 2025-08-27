package com.ggardet.doctor;

import com.ggardet.core.HospitalDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class HospitalEventManager {
    private final DoctorServiceInterface doctorServiceInterface;

    @ApplicationModuleListener
    void onRemovedHospitalEvent(final HospitalDeletedEvent event) {
        final var hospitalId = event.hospitalId();
        log.info("Received removal of hospital {}. Need to unlink doctors", hospitalId);
        final var doctors = doctorServiceInterface.findAllByHospital(hospitalId);
        doctors.forEach(doctorServiceInterface::unlinkDoctorFromHospital);
        log.info("Finished unlinking all doctors from hospital {}.", hospitalId);
    }
}