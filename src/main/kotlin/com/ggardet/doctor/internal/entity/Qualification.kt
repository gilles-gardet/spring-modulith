package com.ggardet.doctor.internal.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "qualification", schema = "doctors")
class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @Column(nullable = false)
    var title: String = ""

    @Column
    var institution: String? = null

    @Column(name = "graduation_date")
    var graduationDate: LocalDate? = null

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    var doctor: Doctor? = null

    // Equals and hashCode based on id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Qualification
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: 0

    override fun toString(): String {
        return "Qualification(id=$id, title='$title', institution='$institution')"
    }
}