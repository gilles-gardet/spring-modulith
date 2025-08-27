package com.ggardet.hospital.spi;

import java.util.UUID;

public interface ExternalInterface {
    void publishDeleteHospitalEvent(final UUID hospitalId);
}
