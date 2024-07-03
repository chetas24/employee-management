package dev.crb.employee_management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.crb.employee_management.Entity.Employee;
import dev.crb.employee_management.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    @Mock
    private MockMvc mockMvc;

    @MockBean
    @Mock
    private EmployeeService employeeService;

    @Autowired
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        Employee employee1 = new Employee(1L, "John", "Doe", "john.doe@example.com", "Manager", 5000.0);
        Employee employee2 = new Employee(2L, "Jane", "Smith", "jane.smith@example.com", "Developer", 4000.0);
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
    }

    @Test
    public void createEmployeeTest() throws Exception {
        Employee newEmployee = new Employee(null, "Alice", "Johnson", "alice.johnson@example.com", "HR", 4500.0);
        Employee savedEmployee = new Employee(1L, "Alice", "Johnson", "alice.johnson@example.com", "HR", 4500.0);

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(savedEmployee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .content(objectMapper.writeValueAsString(newEmployee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    // Add more test methods for other controller endpoints (update, delete, etc.)
}
