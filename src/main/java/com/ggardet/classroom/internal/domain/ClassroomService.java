package com.ggardet.classroom.internal.domain;

import com.ggardet.classroom.ClassroomServiceInterface;
import com.ggardet.classroom.internal.entity.Classroom;
import com.ggardet.classroom.internal.entity.Planning;
import com.ggardet.classroom.internal.repository.ClassroomRepository;
import com.ggardet.classroom.internal.repository.PlanningRepository;
import com.ggardet.classroom.spi.ExternalInterface;
import com.ggardet.classroom.spi.exception.ClassroomEmptyException;
import com.ggardet.classroom.spi.exception.ClassroomFullException;
import com.ggardet.classroom.spi.exception.ClassroomNotFoundException;
import com.ggardet.classroom.spi.exception.PlanningNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
class ClassroomService implements ClassroomServiceInterface {
    private final ClassroomRepository classroomRepository;
    private final PlanningRepository planningRepository;
    private final ExternalInterface externalInterface;

    @Override
    public Classroom createClassroom(final Classroom classroom) {
        final var establishmentId = classroom.getEstablishmentId();
        externalInterface.findEstablishmentById(establishmentId);
        final var currentNumberOfClassrooms = classroomRepository.findByEstablishmentId(establishmentId).size();
        final var maxNumberOfClassroom = externalInterface.getMaxNumberOfClassroomByEstablishmentId(establishmentId);
        if (currentNumberOfClassrooms >= maxNumberOfClassroom) {
            log.error("Cannot add a new classroom. Total number of classrooms for establishment {} has already been reached",
                    establishmentId);
            throw new ClassroomFullException(maxNumberOfClassroom, establishmentId);
        }
        return classroomRepository.save(classroom);
    }

    @Override
    public Classroom findClassroomById(final UUID classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(classroomId, "Classroom"));
    }
    
    @Override
    public List<Classroom> findByEstablishmentId(final UUID establishmentId) {
        final var classroom = classroomRepository.findByEstablishmentId(establishmentId);
        if (classroom.isEmpty()) {
            throw new ClassroomNotFoundException(establishmentId, "Establishment");
        }
        return classroom;
    }

    @Override
    @Transactional
    public void deleteById(final UUID classroomId) {
        classroomRepository.deleteById(classroomId);
    }

    @Override
    public void deleteByEstablishmentId(final UUID establishmentId) {
        classroomRepository.deleteByEstablishmentId(establishmentId);
    }

    @Override
    public Planning addPlanning(final UUID classroomId, final Planning planning) {
        final var classroom = findClassroomById(classroomId);
        planning.setClassroom(classroom);
        return planningRepository.save(planning);
    }

    @Override
    public Planning findPlanningById(final UUID classroomId, final UUID planningId) {
        findClassroomById(classroomId);
        return planningRepository.findById(planningId)
                .orElseThrow(() -> new PlanningNotFoundException(classroomId, planningId));

    }

    @Override
    public List<Planning> findAllPlanningsByClassroomId(final UUID classroomId) {
        findClassroomById(classroomId);
        final var plannings = planningRepository.findAllByClassroomId(classroomId);
        if (plannings.isEmpty()) {
            throw new ClassroomEmptyException(classroomId, "planning");
        }
        return plannings;
    }

    @Override
    public void deletePlanningById(final UUID classroomId, final UUID planningId) {
        findClassroomById(classroomId);
        planningRepository.deleteById(planningId);
    }
}
