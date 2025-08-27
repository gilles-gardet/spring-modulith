package com.ggardet.classroom.spi.external;

import com.ggardet.classroom.spi.ExternalInterface;
import com.ggardet.establishment.EstablishmentServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "classroomExternalService")
@RequiredArgsConstructor
@Slf4j
class ExternalService implements ExternalInterface {
    private final EstablishmentServiceInterface establishmentServiceInterface;

    @Override
    public void findEstablishmentById(final UUID establishmentId) {
        establishmentServiceInterface.findEstablishmentById(establishmentId);
    }

    @Override
    public int getMaxNumberOfClassroomByEstablishmentId(final UUID establishmentId) {
        return establishmentServiceInterface.getMaxNumberOfClassroomByEstablishmentId(establishmentId);
    }

}
