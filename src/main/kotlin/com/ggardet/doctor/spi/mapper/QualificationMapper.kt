package com.ggardet.doctor.spi.mapper

import com.ggardet.doctor.internal.entity.Qualification
import com.ggardet.doctor.spi.dto.QualificationDTO
import org.springframework.stereotype.Component

@Component
class QualificationMapper {
    
    fun toDto(qualification: Qualification): QualificationDTO {
        return QualificationDTO(
            id = qualification.id,
            title = qualification.title,
            institution = qualification.institution,
            graduationDate = qualification.graduationDate
        )
    }

    fun toEntity(qualificationDTO: QualificationDTO): Qualification {
        val qualification = Qualification()
        qualification.id = qualificationDTO.id
        qualification.title = qualificationDTO.title
        qualification.institution = qualificationDTO.institution
        qualification.graduationDate = qualificationDTO.graduationDate
        
        return qualification
    }

    fun toDtos(qualifications: List<Qualification>): List<QualificationDTO> {
        return qualifications.map(::toDto)
    }
}