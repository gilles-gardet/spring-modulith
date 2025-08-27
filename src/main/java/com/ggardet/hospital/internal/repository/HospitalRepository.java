package com.ggardet.hospital.internal.repository;

import com.ggardet.hospital.internal.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}