package com.ggardet.classroom.internal.repository;

import com.ggardet.classroom.internal.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
    void deleteByEstablishmentId(final UUID establishmentId);

    List<Classroom> findByEstablishmentId(final UUID establishmentId);
}
