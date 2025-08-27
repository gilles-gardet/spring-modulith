package com.ggardet.doctor.internal.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "doctor", schema = "doctors")
class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(nullable = false)
    var firstname: String = ""

    @Column(nullable = false)
    var lastname: String = ""

    @Column(nullable = false)
    var specialty: String = ""

    @Column(name = "license_number")
    var licenseNumber: String? = null

    @Column(name = "phone_number")
    var phoneNumber: String? = null

    @Column
    var email: String? = null

    @Column(name = "hospital_id")
    var hospitalId: UUID? = null

    @Column(name = "years_experience")
    var yearsExperience: Int? = null

    // Equals and hashCode based on id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Doctor
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun toString(): String {
        return "Doctor(id=$id, firstname='$firstname', lastname='$lastname', specialty='$specialty')"
    }
}