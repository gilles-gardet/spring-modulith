package com.ggardet.doctor;

import com.ggardet.doctor.spi.dto.DoctorDTO;
import com.ggardet.doctor.spi.dto.QualificationDTO;
import com.ggardet.doctor.spi.dto.SpecialtyDTO;
import com.ggardet.doctor.spi.mapper.DoctorMapper;
import com.ggardet.doctor.spi.mapper.QualificationMapper;
import com.ggardet.doctor.spi.mapper.SpecialtyMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorServiceInterface doctorServiceInterface;
    private final DoctorMapper doctorMapper;
    private final SpecialtyMapper specialtyMapper;
    private final QualificationMapper qualificationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDTO createDoctor(@Valid @RequestBody final DoctorDTO doctorDTO) {
        final var response = doctorServiceInterface.createDoctor(doctorMapper.toEntity(doctorDTO));
        return doctorMapper.toDto(response);
    }

    @GetMapping("/{doctorId}")
    public DoctorDTO findDoctorById(@PathVariable final UUID doctorId) {
        final var response = doctorServiceInterface.findDoctorById(doctorId);
        return doctorMapper.toDto(response);
    }

    @GetMapping
    public List<DoctorDTO> findDoctorsByHospital(@RequestParam final UUID hospitalId) {
        final var response = doctorServiceInterface.findAllByHospital(hospitalId);
        return doctorMapper.toDtos(response);
    }

    @GetMapping("/specialty/{specialty}")
    public List<DoctorDTO> findDoctorsBySpecialty(@PathVariable final String specialty) {
        final var response = doctorServiceInterface.findAllBySpecialty(specialty);
        return doctorMapper.toDtos(response);
    }

    @DeleteMapping("/{doctorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable final UUID doctorId) {
        doctorServiceInterface.deleteDoctor(doctorId);
    }

    @PostMapping("/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    public SpecialtyDTO createSpecialty(@Valid @RequestBody final SpecialtyDTO specialtyDTO) {
        final var response = doctorServiceInterface.createSpecialty(specialtyMapper.toEntity(specialtyDTO));
        return specialtyMapper.toDto(response);
    }

    @GetMapping("/specialties")
    public List<SpecialtyDTO> findAllSpecialties() {
        final var response = doctorServiceInterface.findAllSpecialties();
        return specialtyMapper.toDtos(response);
    }

    @GetMapping("/specialties/{specialtyId}")
    public SpecialtyDTO findSpecialtyById(@PathVariable final UUID specialtyId) {
        final var response = doctorServiceInterface.findSpecialtyById(specialtyId);
        return specialtyMapper.toDto(response);
    }

    @PostMapping("/{doctorId}/qualifications")
    @ResponseStatus(HttpStatus.CREATED)
    public QualificationDTO addQualification(@PathVariable final UUID doctorId, @Valid @RequestBody final QualificationDTO qualificationDTO) {
        final var response = doctorServiceInterface.addQualificationToDoctor(doctorId, qualificationMapper.toEntity(qualificationDTO));
        return qualificationMapper.toDto(response);
    }

    @GetMapping("/{doctorId}/qualifications")
    public List<QualificationDTO> findQualificationsByDoctor(@PathVariable final UUID doctorId) {
        final var response = doctorServiceInterface.findQualificationsByDoctor(doctorId);
        return qualificationMapper.toDtos(response);
    }

    @DeleteMapping("/qualifications/{qualificationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQualification(@PathVariable final UUID qualificationId) {
        doctorServiceInterface.deleteQualification(qualificationId);
    }
}
