package com.ggardet.doctor.internal.repository;

import com.ggardet.doctor.internal.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {
    Optional<Specialty> findByName(final String name);
}