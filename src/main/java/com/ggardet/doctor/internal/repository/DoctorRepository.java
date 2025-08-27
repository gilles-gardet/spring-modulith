package com.ggardet.doctor.internal.repository;

import com.ggardet.doctor.internal.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    List<Doctor> findAllByHospitalId(final UUID hospitalId);

    List<Doctor> findAllBySpecialty(final String specialty);
}
