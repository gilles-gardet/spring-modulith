package com.ggardet.hospital

import com.ggardet.hospital.spi.dto.HospitalDTO
import com.ggardet.hospital.spi.dto.TreatmentDTO
import com.ggardet.hospital.spi.mapper.HospitalMapper
import com.ggardet.hospital.spi.mapper.TreatmentMapper
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/hospitals")
class HospitalController(
    private val hospitalServiceInterface: HospitalServiceInterface,
    private val hospitalMapper: HospitalMapper,
    private val treatmentMapper: TreatmentMapper
) {
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addHospital(@Valid @RequestBody hospitalDTO: HospitalDTO): HospitalDTO {
        val response = hospitalServiceInterface.addHospital(hospitalMapper.toEntity(hospitalDTO))
        return hospitalMapper.toDto(response)
    }

    @GetMapping("/{hospitalId}")
    fun findHospitalById(@PathVariable hospitalId: UUID): HospitalDTO {
        val response = hospitalServiceInterface.findHospitalById(hospitalId)
        return hospitalMapper.toDto(response)
    }

    @DeleteMapping("/{hospitalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteHospital(@PathVariable hospitalId: UUID) {
        hospitalServiceInterface.deleteHospital(hospitalId)
    }

    @PostMapping("/{hospitalId}/treatments")
    @ResponseStatus(HttpStatus.CREATED)
    fun addTreatment(
        @PathVariable hospitalId: UUID,
        @Valid @RequestBody treatmentDTO: TreatmentDTO
    ): TreatmentDTO {
        val response = hospitalServiceInterface.addTreatment(hospitalId, treatmentMapper.toEntity(treatmentDTO))
        return treatmentMapper.toDto(response)
    }

    @GetMapping("/{hospitalId}/treatments/{treatmentId}")
    fun findTreatmentById(
        @PathVariable hospitalId: UUID,
        @PathVariable treatmentId: UUID
    ): TreatmentDTO {
        val response = hospitalServiceInterface.findTreatmentByHospitalIdAndTreatmentId(hospitalId, treatmentId)
        return treatmentMapper.toDto(response)
    }

    @GetMapping("/{hospitalId}/treatments")
    fun findAllTreatmentsByHospitalId(@PathVariable hospitalId: UUID): List<TreatmentDTO> {
        val response = hospitalServiceInterface.findAllTreatmentsByHospitalId(hospitalId)
        return treatmentMapper.toDtos(response)
    }

    @DeleteMapping("/{hospitalId}/treatments/{treatmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTreatmentById(
        @PathVariable hospitalId: UUID,
        @PathVariable treatmentId: UUID
    ) {
        hospitalServiceInterface.deleteTreatment(hospitalId, treatmentId)
    }
}