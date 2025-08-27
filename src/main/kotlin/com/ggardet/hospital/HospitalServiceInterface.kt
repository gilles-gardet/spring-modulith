package com.ggardet.hospital

import com.ggardet.hospital.internal.entity.Hospital
import com.ggardet.hospital.internal.entity.Treatment
import java.util.*

interface HospitalServiceInterface {
    fun addHospital(hospital: Hospital): Hospital
    fun deleteHospital(hospitalId: UUID)
    fun findHospitalById(hospitalId: UUID): Hospital
    
    fun addTreatment(hospitalId: UUID, treatment: Treatment): Treatment
    fun findTreatmentByHospitalIdAndTreatmentId(hospitalId: UUID, treatmentId: UUID): Treatment
    fun findAllTreatmentsByHospitalId(hospitalId: UUID): List<Treatment>
    fun deleteTreatment(hospitalId: UUID, treatmentId: UUID)
}