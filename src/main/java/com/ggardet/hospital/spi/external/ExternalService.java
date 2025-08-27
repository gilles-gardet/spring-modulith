package com.ggardet.hospital.spi.external;

import com.ggardet.hospital.spi.ExternalInterface;
import com.ggardet.core.HospitalDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class ExternalService implements ExternalInterface {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishDeleteHospitalEvent(final UUID hospitalId) {
        eventPublisher.publishEvent(new HospitalDeletedEvent(hospitalId));
    }
}