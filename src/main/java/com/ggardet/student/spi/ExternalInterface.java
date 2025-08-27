package com.ggardet.student.spi;

import java.util.UUID;

public interface ExternalInterface {
    void findEstablishmentById(final UUID establishmentId);

    void findClassroomById(final UUID classroomId);
}
