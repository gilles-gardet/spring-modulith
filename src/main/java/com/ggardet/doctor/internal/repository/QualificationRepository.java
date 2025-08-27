package com.ggardet.doctor.internal.repository;

import com.ggardet.doctor.internal.entity.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QualificationRepository extends JpaRepository<Qualification, UUID> {
    List<Qualification> findAllByDoctorId(final UUID doctorId);
}