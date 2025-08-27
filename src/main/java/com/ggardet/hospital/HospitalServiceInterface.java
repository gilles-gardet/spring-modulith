package com.ggardet.hospital;

import com.ggardet.hospital.internal.entity.Hospital;
import com.ggardet.hospital.internal.entity.Treatment;

import java.util.List;
import java.util.UUID;

public interface HospitalServiceInterface {
    Hospital addHospital(final Hospital hospital);
    void deleteHospital(final UUID hospitalId);
    Hospital findHospitalById(final UUID hospitalId);
    
    Treatment addTreatment(final UUID hospitalId, final Treatment treatment);
    Treatment findTreatmentByHospitalIdAndTreatmentId(final UUID hospitalId, final UUID treatmentId);
    List<Treatment> findAllTreatmentsByHospitalId(final UUID hospitalId);
    void deleteTreatment(final UUID hospitalId, final UUID treatmentId);
}
