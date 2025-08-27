package com.ggardet.student;

import com.ggardet.student.spi.dto.StudentDTO;
import com.ggardet.student.spi.mapper.StudentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceInterface studentServiceInterface;
    private final StudentMapper studentMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO addStudent(@Valid @RequestBody final StudentDTO studentDTO) {
        final var entity = studentMapper.toEntity(studentDTO);
        final var response = studentServiceInterface.createStudent(entity);
        return studentMapper.toDto(response);
    }

    @GetMapping("/{studentId}")
    public StudentDTO findStudentById(@PathVariable final UUID studentId) {
        final var response = studentServiceInterface.findStudentById(studentId);
        return studentMapper.toDto(response);
    }

    @DeleteMapping("/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable final UUID studentId) {
        studentServiceInterface.deleteStudent(studentId);
    }
}
