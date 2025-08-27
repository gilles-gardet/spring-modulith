package com.ggardet.doctor.internal.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "specialty", schema = "doctors")
class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(nullable = false, unique = true)
    var name: String = ""

    @Column
    var description: String? = null

    @Column(name = "required_years_training")
    var requiredYearsTraining: Int? = null

    // Equals and hashCode based on id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Specialty
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun toString(): String {
        return "Specialty(id=$id, name='$name', description='$description')"
    }
}