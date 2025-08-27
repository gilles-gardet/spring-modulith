package com.ggardet.classroom.spi;

import java.util.UUID;

public interface ExternalInterface {
    void findEstablishmentById(final UUID establishmentId);

    int getMaxNumberOfClassroomByEstablishmentId(final UUID establishmentId);
}
