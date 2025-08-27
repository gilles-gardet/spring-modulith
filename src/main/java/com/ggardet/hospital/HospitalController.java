package com.ggardet.hospital;

import com.ggardet.hospital.spi.dto.HospitalDTO;
import com.ggardet.hospital.spi.dto.TreatmentDTO;
import com.ggardet.hospital.spi.mapper.HospitalMapper;
import com.ggardet.hospital.spi.mapper.TreatmentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalServiceInterface hospitalServiceInterface;
    private final HospitalMapper hospitalMapper;
    private final TreatmentMapper treatmentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HospitalDTO addHospital(@Valid @RequestBody final HospitalDTO hospitalDTO) {
        final var response = hospitalServiceInterface.addHospital(hospitalMapper.toEntity(hospitalDTO));
        return hospitalMapper.toDto(response);
    }

    @GetMapping("/{hospitalId}")
    public HospitalDTO findHospitalById(@PathVariable final UUID hospitalId) {
        final var response = hospitalServiceInterface.findHospitalById(hospitalId);
        return hospitalMapper.toDto(response);
    }

    @DeleteMapping("/{hospitalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHospital(@PathVariable final UUID hospitalId) {
        hospitalServiceInterface.deleteHospital(hospitalId);
    }

    @PostMapping("/{hospitalId}/treatments")
    @ResponseStatus(HttpStatus.CREATED)
    public TreatmentDTO addTreatment(@PathVariable final UUID hospitalId, @Valid @RequestBody final TreatmentDTO treatmentDTO) {
        final var response = hospitalServiceInterface.addTreatment(hospitalId, treatmentMapper.toEntity(treatmentDTO));
        return treatmentMapper.toDto(response);
    }

    @GetMapping("/{hospitalId}/treatments/{treatmentId}")
    public TreatmentDTO findTreatmentById(@PathVariable final UUID hospitalId, @PathVariable final UUID treatmentId) {
        final var response = hospitalServiceInterface.findTreatmentByHospitalIdAndTreatmentId(hospitalId, treatmentId);
        return treatmentMapper.toDto(response);
    }

    @GetMapping("/{hospitalId}/treatments")
    public List<TreatmentDTO> findAllTreatmentsByHospitalId(@PathVariable final UUID hospitalId) {
        final var response = hospitalServiceInterface.findAllTreatmentsByHospitalId(hospitalId);
        return treatmentMapper.toDtos(response);
    }

    @DeleteMapping("/{hospitalId}/treatments/{treatmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTreatmentById(@PathVariable final UUID hospitalId, @PathVariable final UUID treatmentId) {
        hospitalServiceInterface.deleteTreatment(hospitalId, treatmentId);
    }
}