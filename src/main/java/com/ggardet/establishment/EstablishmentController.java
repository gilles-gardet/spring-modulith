package com.ggardet.establishment;

import com.ggardet.establishment.spi.dto.ActivityDTO;
import com.ggardet.establishment.spi.dto.EmployeeDTO;
import com.ggardet.establishment.spi.dto.EstablishmentDTO;
import com.ggardet.establishment.spi.mapper.ActivityMapper;
import com.ggardet.establishment.spi.mapper.EmployeeMapper;
import com.ggardet.establishment.spi.mapper.EstablishmentMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/establishments")
@RequiredArgsConstructor
public class EstablishmentController {
    private final EstablishmentServiceInterface establishmentServiceInterface;
    private final EstablishmentMapper establishmentMapper;
    private final EmployeeMapper employeeMapper;
    private final ActivityMapper activityMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentDTO addEstablishment(@Valid @RequestBody final EstablishmentDTO establishmentDTO) {
        final var response = establishmentServiceInterface.addEstablishment(establishmentMapper.toEntity(establishmentDTO));
        return establishmentMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}")
    public EstablishmentDTO findEstablishmentById(@PathVariable final UUID establishmentId) {
        final var response = establishmentServiceInterface.findEstablishmentById(establishmentId);
        return establishmentMapper.toDto(response);
    }

    @DeleteMapping("/{establishmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEstablishment(@PathVariable final UUID establishmentId) {
        establishmentServiceInterface.deleteEstablishment(establishmentId);
    }

    @PostMapping("/{establishmentId}/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO addEmployee(@PathVariable final UUID establishmentId, @Valid @RequestBody final EmployeeDTO employeeDTO) {
        final var response = establishmentServiceInterface.addEmployee(establishmentId, employeeMapper.toEntity(employeeDTO));
        return employeeMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/employees/{employeeId}")
    public EmployeeDTO findEmployeeById(@PathVariable final UUID establishmentId, @PathVariable final UUID employeeId) {
        final var response = establishmentServiceInterface.findEmployeeByEstablishmentIdAndEmployeeId(establishmentId, employeeId);
        return employeeMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/employees")
    public List<EmployeeDTO> findAllEmployeeByEstablishmentId(@PathVariable final UUID establishmentId) {
        final var response = establishmentServiceInterface.findAllEmployeesByEstablishmentId(establishmentId);
        return employeeMapper.toDtos(response);
    }

    @DeleteMapping("/{establishmentId}/employees/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable final UUID establishmentId, @PathVariable final UUID employeeId) {
        establishmentServiceInterface.deleteEmployee(establishmentId, employeeId);
    }

    @PostMapping("/{establishmentId}/activities")
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDTO addActivity(@PathVariable final UUID establishmentId, @Valid @RequestBody final ActivityDTO activityDTO) {
        var response = establishmentServiceInterface.addActivity(establishmentId, activityMapper.toEntity(activityDTO));
        return activityMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/activities/{activityId}")
    public ActivityDTO findActivityById(@PathVariable final UUID establishmentId, @PathVariable final UUID activityId) {
        var response = establishmentServiceInterface.findActivityByEstablishmentIdAndActivityId(establishmentId, activityId);
        return activityMapper.toDto(response);
    }

    @GetMapping("/{establishmentId}/activities")
    public List<ActivityDTO> findAllActivitiesByEstablishmentId(@PathVariable final UUID establishmentId) {
        var response = establishmentServiceInterface.findAllActivitiesByEstablishmentId(establishmentId);
        return activityMapper.toDtos(response);
    }

    @DeleteMapping("/{establishmentId}/activities/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAllActivityById(@PathVariable final UUID establishmentId, @PathVariable final UUID activityId) {
        establishmentServiceInterface.deleteActivity(establishmentId, activityId);
    }
}
