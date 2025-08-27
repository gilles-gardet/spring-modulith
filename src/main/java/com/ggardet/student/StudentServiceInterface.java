package com.ggardet.student;

import com.ggardet.student.internal.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentServiceInterface {
    Student createStudent(final Student student);

    void deleteStudent(final UUID studentId);

    void unlinkStudentToEstablishment(final Student student);

    List<Student> findAllByEstablishmentId(final UUID establishmentId);

    Student findStudentById(final UUID studentId);
}
