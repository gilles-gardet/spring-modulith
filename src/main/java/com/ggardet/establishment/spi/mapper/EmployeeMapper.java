package com.ggardet.establishment.spi.mapper;

import com.ggardet.establishment.internal.entity.Employee;
import com.ggardet.establishment.spi.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "establishment", ignore = true)
    Employee toEntity(final EmployeeDTO employeeDTO);

    @Mapping(target = "establishmentId", ignore = true)
    EmployeeDTO toDto(final Employee employee);

    List<EmployeeDTO> toDtos(final List<Employee> employees);
}
