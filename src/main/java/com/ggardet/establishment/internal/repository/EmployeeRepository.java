package com.ggardet.establishment.internal.repository;

import com.ggardet.establishment.internal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    List<Employee> findAllByEstablishmentId(final UUID establishmentId);
}
