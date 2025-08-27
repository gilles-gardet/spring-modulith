package com.ggardet.patient.internal.domain;

import com.ggardet.core.HospitalDeletedEvent;
import com.ggardet.patient.internal.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HospitalEventListener {
    private final PatientRepository patientRepository;

    @ApplicationModuleListener
    public void on(final HospitalDeletedEvent event) {
        log.info("Handling hospital deletion event for hospital: {}", event.hospitalId());
        final var patients = patientRepository.findByHospitalId(event.hospitalId());
        if (!patients.isEmpty()) {
            log.info("Updating {} patients for hospital: {}", patients.size(), event.hospitalId());
            patients.forEach(patient -> {
                patient.setHospitalId(null);
                patient.setServiceId(null);
            });
            patientRepository.saveAll(patients);
        }
    }
}