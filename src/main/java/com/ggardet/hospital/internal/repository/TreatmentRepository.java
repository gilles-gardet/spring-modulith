package com.ggardet.hospital.internal.repository;

import com.ggardet.hospital.internal.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TreatmentRepository extends JpaRepository<Treatment, UUID> {
    List<Treatment> findAllByHospitalId(final UUID hospitalId);
}
