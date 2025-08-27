package com.ggardet.hospital.internal.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "treatment", schema = "hospitals")
data class Treatment(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column
    var description: String? = null,

    @Column
    var durationMinutes: Int = 0,

    @Column
    var protocol: String? = null,

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    var hospital: Hospital? = null
)