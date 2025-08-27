package com.ggardet.establishment.internal.domain;

import com.ggardet.establishment.EstablishmentServiceInterface;
import com.ggardet.establishment.internal.entity.Activity;
import com.ggardet.establishment.internal.entity.Employee;
import com.ggardet.establishment.internal.entity.Establishment;
import com.ggardet.establishment.internal.repository.ActivityRepository;
import com.ggardet.establishment.internal.repository.EmployeeRepository;
import com.ggardet.establishment.internal.repository.EstablishmentRepository;
import com.ggardet.establishment.spi.ExternalInterface;
import com.ggardet.establishment.spi.exception.ActivityNotFoundException;
import com.ggardet.establishment.spi.exception.EmployeeNotFoundException;
import com.ggardet.establishment.spi.exception.EstablishmentEmptyException;
import com.ggardet.establishment.spi.exception.EstablishmentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class EstablishmentService implements EstablishmentServiceInterface {
    private final ExternalInterface externalInterface;
    private final EstablishmentRepository establishmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ActivityRepository activityRepository;

    @Override
    public Establishment addEstablishment(final Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    @Override
    @Transactional
    public void deleteEstablishment(final UUID establishmentId) {
        establishmentRepository.deleteById(establishmentId);
        externalInterface.publishDeleteEstablishmentEvent(establishmentId);
    }

    @Override
    public int getMaxNumberOfClassroomByEstablishmentId(final UUID establishmentId) {
        final var establishment = establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));
        return establishment.getNbMaxClassroom();
    }

    @Override
    public Establishment findEstablishmentById(final UUID establishmentId) {
        return establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException(establishmentId));
    }

    @Override
    public Employee addEmployee(final UUID establishmentId, final Employee employee) {
        final var establishment = findEstablishmentById(establishmentId);
        employee.setEstablishment(establishment);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeByEstablishmentIdAndEmployeeId(final UUID establishmentId, final UUID employeeId) {
        findEstablishmentById(establishmentId);
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(establishmentId, employeeId));
    }

    @Override
    public List<Employee> findAllEmployeesByEstablishmentId(final UUID establishmentId) {
        findEstablishmentById(establishmentId);
        final var employees = employeeRepository.findAllByEstablishmentId(establishmentId);
        if (employees.isEmpty()) {
            throw new EstablishmentEmptyException(establishmentId, "employee");
        }
        return employees;
    }

    @Override
    public void deleteEmployee(final UUID establishmentId, final UUID employeeId) {
        findEstablishmentById(establishmentId);
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Activity addActivity(final UUID establishmentId, final Activity activity) {
        final var establishment = findEstablishmentById(establishmentId);
        activity.setEstablishment(establishment);
        return activityRepository.save(activity);
    }

    @Override
    public Activity findActivityByEstablishmentIdAndActivityId(final UUID establishmentId, final UUID activityId) {
        findEstablishmentById(establishmentId);
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(establishmentId, activityId));
    }

    @Override
    public List<Activity> findAllActivitiesByEstablishmentId(final UUID establishmentId) {
        findEstablishmentById(establishmentId);
        final var activities = activityRepository.findAllByEstablishmentId(establishmentId);
        if (activities.isEmpty()) {
            throw new EstablishmentEmptyException(establishmentId, "activity");
        }
        return activities;
    }

    @Override
    public void deleteActivity(final UUID establishmentId, final UUID activityId) {
        findEstablishmentById(establishmentId);
        activityRepository.deleteById(activityId);
    }
}
