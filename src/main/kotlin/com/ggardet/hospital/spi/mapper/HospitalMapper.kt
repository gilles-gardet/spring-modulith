package com.ggardet.hospital.spi.mapper

import com.ggardet.hospital.internal.entity.Hospital
import com.ggardet.hospital.spi.dto.HospitalDTO
import org.springframework.stereotype.Component

@Component
class HospitalMapper {
    
    fun toDto(hospital: Hospital): HospitalDTO {
        return HospitalDTO(
            id = hospital.id,
            name = hospital.name,
            address = hospital.address,
            nbMaxServices = hospital.nbMaxServices,
            phoneNumber = hospital.phoneNumber,
            director = hospital.director
        )
    }

    fun toEntity(hospitalDTO: HospitalDTO): Hospital {
        return Hospital(
            id = hospitalDTO.id,
            name = hospitalDTO.name,
            address = hospitalDTO.address,
            nbMaxServices = hospitalDTO.nbMaxServices,
            phoneNumber = hospitalDTO.phoneNumber,
            director = hospitalDTO.director
        )
    }

    fun toDtos(hospitals: List<Hospital>): List<HospitalDTO> {
        return hospitals.map(::toDto)
    }
}