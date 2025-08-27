package com.ggardet.student.internal.domain;

import com.ggardet.student.StudentServiceInterface;
import com.ggardet.student.internal.entity.Student;
import com.ggardet.student.internal.repository.StudentRepository;
import com.ggardet.student.spi.ExternalInterface;
import com.ggardet.student.spi.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class StudentService implements StudentServiceInterface {
    private final StudentRepository studentRepository;
    private final ExternalInterface externalInterface;

    @Override
    public Student createStudent(final Student student) {
        if (Objects.nonNull(student.getEstablishmentId())) {
            externalInterface.findEstablishmentById(student.getClassroomId());
        }
        if (Objects.nonNull(student.getClassroomId())) {
            externalInterface.findClassroomById(student.getClassroomId());
        }
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(final UUID studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void unlinkStudentToEstablishment(final Student student) {
        student.setEstablishmentId(null);
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAllByEstablishmentId(final UUID establishmentId) {
        return studentRepository.findAllByEstablishmentId(establishmentId);
    }

    @Override
    public Student findStudentById(final UUID studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }
}
