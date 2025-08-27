package com.ggardet.hospital.internal.domain

import com.ggardet.hospital.HospitalServiceInterface
import com.ggardet.hospital.internal.entity.Hospital
import com.ggardet.hospital.internal.entity.Treatment
import com.ggardet.hospital.internal.repository.HospitalRepository
import com.ggardet.hospital.internal.repository.TreatmentRepository
import com.ggardet.hospital.spi.ExternalInterface
import com.ggardet.hospital.spi.exception.HospitalEmptyException
import com.ggardet.hospital.spi.exception.HospitalNotFoundException
import com.ggardet.hospital.spi.exception.TreatmentNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
internal class HospitalService(
    private val externalInterface: ExternalInterface,
    private val hospitalRepository: HospitalRepository,
    private val treatmentRepository: TreatmentRepository
) : HospitalServiceInterface {

    override fun addHospital(hospital: Hospital): Hospital {
        return hospitalRepository.save(hospital)
    }

    @Transactional
    override fun deleteHospital(hospitalId: UUID) {
        hospitalRepository.deleteById(hospitalId)
        externalInterface.publishDeleteHospitalEvent(hospitalId)
    }

    override fun findHospitalById(hospitalId: UUID): Hospital {
        return hospitalRepository.findById(hospitalId)
            .orElseThrow { HospitalNotFoundException(hospitalId) }
    }

    override fun addTreatment(hospitalId: UUID, treatment: Treatment): Treatment {
        val hospital = findHospitalById(hospitalId)
        treatment.hospital = hospital
        return treatmentRepository.save(treatment)
    }

    override fun findTreatmentByHospitalIdAndTreatmentId(hospitalId: UUID, treatmentId: UUID): Treatment {
        findHospitalById(hospitalId)
        return treatmentRepository.findById(treatmentId)
            .orElseThrow { TreatmentNotFoundException(hospitalId, treatmentId) }
    }

    override fun findAllTreatmentsByHospitalId(hospitalId: UUID): List<Treatment> {
        findHospitalById(hospitalId)
        val treatments = treatmentRepository.findAllByHospitalId(hospitalId)
        if (treatments.isEmpty()) {
            throw HospitalEmptyException(hospitalId, "treatment")
        }
        return treatments
    }

    override fun deleteTreatment(hospitalId: UUID, treatmentId: UUID) {
        findHospitalById(hospitalId)
        treatmentRepository.deleteById(treatmentId)
    }
}