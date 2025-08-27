package com.ggardet.doctor.internal.domain;

import com.ggardet.doctor.DoctorServiceInterface;
import com.ggardet.doctor.internal.entity.Doctor;
import com.ggardet.doctor.internal.entity.Qualification;
import com.ggardet.doctor.internal.entity.Specialty;
import com.ggardet.doctor.internal.repository.DoctorRepository;
import com.ggardet.doctor.internal.repository.QualificationRepository;
import com.ggardet.doctor.internal.repository.SpecialtyRepository;
import com.ggardet.doctor.spi.exception.DoctorNotFoundException;
import com.ggardet.doctor.spi.exception.SpecialtyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class DoctorService implements DoctorServiceInterface {
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final QualificationRepository qualificationRepository;

    @Override
    public Doctor createDoctor(final Doctor doctor) {
        if (Objects.nonNull(doctor.getSpecialty())) {
            specialtyRepository.findByName(doctor.getSpecialty())
                    .orElseThrow(() -> new SpecialtyNotFoundException(doctor.getSpecialty()));
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findDoctorById(final UUID doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId));
    }

    @Override
    public List<Doctor> findAllByHospital(final UUID hospitalId) {
        return doctorRepository.findAllByHospitalId(hospitalId);
    }

    @Override
    public List<Doctor> findAllBySpecialty(final String specialty) {
        return doctorRepository.findAllBySpecialty(specialty);
    }

    @Override
    public void deleteDoctor(final UUID doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    @Transactional
    public void unlinkDoctorFromHospital(final Doctor doctor) {
        doctor.setHospitalId(null);
        doctorRepository.save(doctor);
    }

    @Override
    public Specialty createSpecialty(final Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty findSpecialtyById(final UUID specialtyId) {
        return specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new SpecialtyNotFoundException(specialtyId));
    }

    @Override
    public List<Specialty> findAllSpecialties() {
        return specialtyRepository.findAll();
    }

    @Override
    public Qualification addQualificationToDoctor(final UUID doctorId, final Qualification qualification) {
        final var doctor = findDoctorById(doctorId);
        qualification.setDoctor(doctor);
        return qualificationRepository.save(qualification);
    }

    @Override
    public List<Qualification> findQualificationsByDoctor(final UUID doctorId) {
        findDoctorById(doctorId);
        return qualificationRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public void deleteQualification(final UUID qualificationId) {
        qualificationRepository.deleteById(qualificationId);
    }
}
