package com.ggardet.establishment.spi;

import java.util.UUID;

public interface ExternalInterface {
    void publishDeleteEstablishmentEvent(final UUID establishmentId);
}
