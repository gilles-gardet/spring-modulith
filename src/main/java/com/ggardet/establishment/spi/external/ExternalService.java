package com.ggardet.establishment.spi.external;

import com.ggardet.establishment.spi.ExternalInterface;
import com.ggardet.establishment.spi.event.EstablishmentDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service(value = "establishmentExternalService")
@RequiredArgsConstructor
class ExternalService implements ExternalInterface {
    private final ApplicationEventPublisher event;

    @Override
    public void publishDeleteEstablishmentEvent(final UUID establishmentId) {
        event.publishEvent(new EstablishmentDeletedEvent(establishmentId));
    }
}
