package com.ggardet.establishment;

import com.ggardet.establishment.internal.entity.Activity;
import com.ggardet.establishment.internal.entity.Employee;
import com.ggardet.establishment.internal.entity.Establishment;

import java.util.List;
import java.util.UUID;

public interface EstablishmentServiceInterface {
    Establishment addEstablishment(final Establishment establishment);

    void deleteEstablishment(final UUID establishmentId);

    int getMaxNumberOfClassroomByEstablishmentId(final UUID establishmentId);

    Employee addEmployee(final UUID establishmentId, final Employee employee);

    Establishment findEstablishmentById(final UUID establishmentId);

    Employee findEmployeeByEstablishmentIdAndEmployeeId(final UUID establishmentId, final UUID employeeId);

    List<Employee> findAllEmployeesByEstablishmentId(final UUID establishmentId);

    Activity addActivity(final UUID establishmentId, final Activity entity);

    void deleteEmployee(final UUID establishmentId, final UUID employeeId);

    void deleteActivity(final UUID establishmentId, final UUID activityId);

    List<Activity> findAllActivitiesByEstablishmentId(final UUID establishmentId);

    Activity findActivityByEstablishmentIdAndActivityId(final UUID establishmentId, final UUID activityId);
}
