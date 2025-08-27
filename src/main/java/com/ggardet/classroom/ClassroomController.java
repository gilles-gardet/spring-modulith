package com.ggardet.classroom;

import com.ggardet.classroom.spi.dto.ClassroomDTO;
import com.ggardet.classroom.spi.dto.PlanningDTO;
import com.ggardet.classroom.spi.mapper.ClassroomMapper;
import com.ggardet.classroom.spi.mapper.PlanningMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomServiceInterface classroomServiceInterface;
    private final ClassroomMapper classroomMapper;
    private final PlanningMapper planningMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassroomDTO createClassroom(@Valid @RequestBody final ClassroomDTO classroomDTO) {
        final var response = classroomServiceInterface.createClassroom(classroomMapper.toEntity(classroomDTO));
        return classroomMapper.toDto(response);
    }

    @GetMapping("/{classroomId}")
    public ClassroomDTO findById(@PathVariable final UUID classroomId) {
        final var response = classroomServiceInterface.findClassroomById(classroomId);
        return classroomMapper.toDto(response);
    }

    @GetMapping
    public List<ClassroomDTO> findByEstablishmentId(@RequestParam final UUID establishmentId) {
        final var response = classroomServiceInterface.findByEstablishmentId(establishmentId);
        return classroomMapper.toDtos(response);
    }

    @DeleteMapping("/{classroomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassroom(@PathVariable final UUID classroomId) {
        classroomServiceInterface.deleteById(classroomId);
    }

    @PostMapping("/{classroomId}/plannings")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanningDTO addPlanning(@PathVariable final UUID classroomId, @Valid @RequestBody final PlanningDTO planningDTO) {
        final var response = classroomServiceInterface.addPlanning(classroomId, planningMapper.toEntity(planningDTO));
        return planningMapper.toDto(response);
    }

    @GetMapping("/{classroomId}/plannings/{planningId}")
    public PlanningDTO findEmployeeById(@PathVariable final UUID classroomId, @PathVariable final UUID planningId) {
        final var response = classroomServiceInterface.findPlanningById(classroomId, planningId);
        return planningMapper.toDto(response);
    }

    @GetMapping("/{classroomId}/plannings")
    public List<PlanningDTO> findAllEmployeeByEstablishmentId(@PathVariable final UUID classroomId) {
        final var response = classroomServiceInterface.findAllPlanningsByClassroomId(classroomId);
        return planningMapper.toDtos(response);
    }

    @DeleteMapping("/{classroomId}/plannings/{planningId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable final UUID classroomId, @PathVariable final UUID planningId) {
        classroomServiceInterface.deletePlanningById(classroomId, planningId);
    }
}
