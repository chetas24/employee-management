package dev.crb.employee_management.service;

import dev.crb.employee_management.Entity.Employee;
import dev.crb.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.open(this);
    }

    @Test
    public void testSaveEmployee() {
        // Given
        Employee employee = new Employee(null, "John", "Doe", "john.doe@example.com", "Manager", 5000.0);
        when(employeeRepository.save(employee)).thenReturn(employee);

        // When
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // Then
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals("john.doe@example.com", savedEmployee.getEmail());
        assertEquals("Manager", savedEmployee.getPosition());
        assertEquals(5000.0, savedEmployee.getSalary());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeById() {
        // Given
        Long id = 1L;
        Employee employee = new Employee(id, "Jane", "Smith", "jane.smith@example.com", "Developer", 4000.0);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // When
        Employee foundEmployee = employeeService.getEmployeeById(id);

        // Then
        assertEquals("Jane", foundEmployee.getFirstName());
        assertEquals("Smith", foundEmployee.getLastName());
        assertEquals("jane.smith@example.com", foundEmployee.getEmail());
        assertEquals("Developer", foundEmployee.getPosition());
        assertEquals(4000.0, foundEmployee.getSalary());
        verify(employeeRepository, times(1)).findById(id);
    }

    // Add more test methods for other service methods (getAllEmployees, updateEmployee, deleteEmployee, etc.)
}
