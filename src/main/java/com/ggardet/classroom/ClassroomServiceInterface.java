package com.ggardet.classroom;

import com.ggardet.classroom.internal.entity.Classroom;
import com.ggardet.classroom.internal.entity.Planning;

import java.util.List;
import java.util.UUID;

public interface ClassroomServiceInterface {
    Classroom createClassroom(final Classroom classroom);

    void deleteById(final UUID classroomId);

    Classroom findClassroomById(final UUID classroomId);

    void deleteByEstablishmentId(final UUID establishmentId);

    List<Classroom> findByEstablishmentId(final UUID establishmentId);

    Planning addPlanning(final UUID classroomId, final Planning planning);

    Planning findPlanningById(final UUID classroomId, final UUID planningId);

    List<Planning> findAllPlanningsByClassroomId(final UUID classroomId);

    void deletePlanningById(final UUID classroomId, final UUID planningId);
}
