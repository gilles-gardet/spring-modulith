package com.ggardet.hospital.internal.domain;

import com.ggardet.hospital.HospitalServiceInterface;
import com.ggardet.hospital.internal.entity.Hospital;
import com.ggardet.hospital.internal.entity.Treatment;
import com.ggardet.hospital.internal.repository.HospitalRepository;
import com.ggardet.hospital.internal.repository.TreatmentRepository;
import com.ggardet.hospital.spi.ExternalInterface;
import com.ggardet.hospital.spi.exception.HospitalEmptyException;
import com.ggardet.hospital.spi.exception.HospitalNotFoundException;
import com.ggardet.hospital.spi.exception.TreatmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class HospitalService implements HospitalServiceInterface {
    private final ExternalInterface externalInterface;
    private final HospitalRepository hospitalRepository;
    private final TreatmentRepository treatmentRepository;

    @Override
    public Hospital addHospital(final Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    @Transactional
    public void deleteHospital(final UUID hospitalId) {
        hospitalRepository.deleteById(hospitalId);
        externalInterface.publishDeleteHospitalEvent(hospitalId);
    }

    @Override
    public Hospital findHospitalById(final UUID hospitalId) {
        return hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundException(hospitalId));
    }

    @Override
    public Treatment addTreatment(final UUID hospitalId, final Treatment treatment) {
        final var hospital = findHospitalById(hospitalId);
        treatment.setHospital(hospital);
        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment findTreatmentByHospitalIdAndTreatmentId(final UUID hospitalId, final UUID treatmentId) {
        findHospitalById(hospitalId);
        return treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new TreatmentNotFoundException(hospitalId, treatmentId));
    }

    @Override
    public List<Treatment> findAllTreatmentsByHospitalId(final UUID hospitalId) {
        findHospitalById(hospitalId);
        final var treatments = treatmentRepository.findAllByHospitalId(hospitalId);
        if (treatments.isEmpty()) {
            throw new HospitalEmptyException(hospitalId, "treatment");
        }
        return treatments;
    }

    @Override
    public void deleteTreatment(final UUID hospitalId, final UUID treatmentId) {
        findHospitalById(hospitalId);
        treatmentRepository.deleteById(treatmentId);
    }
}
