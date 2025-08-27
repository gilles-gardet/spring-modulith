package com.ggardet.student.spi.external;

import com.ggardet.classroom.ClassroomServiceInterface;
import com.ggardet.establishment.EstablishmentServiceInterface;
import com.ggardet.student.spi.ExternalInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "studentExternalService")
@RequiredArgsConstructor
class ExternalService implements ExternalInterface {
    private final EstablishmentServiceInterface establishmentServiceInterface;
    private final ClassroomServiceInterface classroomServiceInterface;

    @Override
    public void findEstablishmentById(final UUID establishmentId) {
        establishmentServiceInterface.findEstablishmentById(establishmentId);
    }

    @Override
    public void findClassroomById(final UUID classroomId) {
        classroomServiceInterface.findClassroomById(classroomId);
    }
}
