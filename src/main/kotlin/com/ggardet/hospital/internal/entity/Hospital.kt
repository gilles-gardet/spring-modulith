package com.ggardet.hospital.internal.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "hospital", schema = "hospitals")
data class Hospital(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,
    
    @Column(nullable = false)
    var name: String,
    
    @Column(nullable = false)
    var address: String,
    
    @Column(name = "nb_max_services")
    var nbMaxServices: Int,
    
    @Column
    var phoneNumber: String? = null,
    
    @Column
    var director: String? = null
)