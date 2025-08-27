package com.ggardet.student.internal.repository;

import com.ggardet.student.internal.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {
    List<Student> findAllByEstablishmentId(final UUID establishmentId);
}
