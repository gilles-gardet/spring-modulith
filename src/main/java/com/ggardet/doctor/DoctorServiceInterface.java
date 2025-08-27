package com.ggardet.doctor;

import com.ggardet.doctor.internal.entity.Doctor;
import com.ggardet.doctor.internal.entity.Qualification;
import com.ggardet.doctor.internal.entity.Specialty;

import java.util.List;
import java.util.UUID;

public interface DoctorServiceInterface {
    Doctor createDoctor(final Doctor doctor);

    Doctor findDoctorById(final UUID doctorId);

    List<Doctor> findAllByHospital(final UUID hospitalId);

    List<Doctor> findAllBySpecialty(final String specialty);

    void deleteDoctor(final UUID doctorId);

    void unlinkDoctorFromHospital(final Doctor doctor);

    Specialty createSpecialty(final Specialty specialty);

    Specialty findSpecialtyById(final UUID specialtyId);

    List<Specialty> findAllSpecialties();

    Qualification addQualificationToDoctor(final UUID doctorId, final Qualification qualification);

    List<Qualification> findQualificationsByDoctor(final UUID doctorId);

    void deleteQualification(final UUID qualificationId);
}
