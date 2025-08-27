package com.ggardet.student;

import com.ggardet.establishment.spi.event.EstablishmentDeletedEvent;
import com.ggardet.student.spi.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class EstablishmentEventManager {
    private final StudentServiceInterface studentServiceInterface;
    private final StudentMapper studentMapper;

    @ApplicationModuleListener
    void onRemovedEstablishmentEvent(final EstablishmentDeletedEvent event) {
        final var establishmentId = event.establishmentId();
        log.info("Received removal of establishment {}. We need to update the linked students", establishmentId);
        final var studentDTOS =
                studentMapper.toDtos(studentServiceInterface.findAllByEstablishmentId(establishmentId));
        studentDTOS.forEach(studentDTO -> studentServiceInterface.unlinkStudentToEstablishment(studentMapper.toEntity(studentDTO)));
        log.info("Finished unlinking all students of establishment {}.", establishmentId);
    }
}
