package dev.crb.employee_management.repository;

import dev.crb.employee_management.Entity.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

    @Autowired
    @Mock
    private TestEntityManager entityManager;

    @Autowired
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void testSaveEmployee() {
        // Given
        Employee employee = new Employee(null, "John", "Doe", "john.doe@example.com", "Manager", 5000.0);

        // When
        Employee savedEmployee = employeeRepository.save(employee);

        // Then
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals("john.doe@example.com", savedEmployee.getEmail());
        assertEquals("Manager", savedEmployee.getPosition());
        assertEquals(5000.0, savedEmployee.getSalary());
    }

    @Test
    public void testFindAllEmployees() {
        // Given
        Employee employee1 = new Employee(null, "John", "Doe", "john.doe@example.com", "Manager", 5000.0);
        Employee employee2 = new Employee(null, "Jane", "Smith", "jane.smith@example.com", "Developer", 4000.0);
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.flush();

        // When
        List<Employee> employees = employeeRepository.findAll();

        // Then
        assertEquals(2, employees.size());
    }

    // Add more test methods for other repository methods (findById, deleteById, etc.)
}
