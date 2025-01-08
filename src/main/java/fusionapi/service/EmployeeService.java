package fusionapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fusionapi.Exception.FusionApiException;
import fusionapi.model.Employee;
import fusionapi.repository.jpa.EmployeeJpaRepository;
import fusionapi.repository.mongo.EmployeeMongoRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMongoRepository employeeMongoRepository;

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    // Create or Update an Employee
    public Employee saveEmployee(Employee employee, boolean useMongo) {
        try {
            if (useMongo) {
                employee.setMongoId(null); // Ensure MongoDB auto-generates the ID if not provided
                return employeeMongoRepository.save(employee); // Save to MongoDB
            } else {
                employee.setId(null); // Ensure SQL auto-generates the ID if not provided
                return employeeJpaRepository.save(employee); // Save to MySQL (JPA)
            }
        } catch (DataIntegrityViolationException e) {
            throw new FusionApiException("Data Integrity Violation: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new FusionApiException("Error Saving Employee: " + e.getMessage(), e);
        }
    }

    // Get Employee by ID
    public Employee getEmployeeById(String id, boolean useMongo) {
        try {
            if (useMongo) {
                Optional<Employee> employee = employeeMongoRepository.findById(id);
                return employee.orElseThrow(() -> new FusionApiException("Employee with ID " + id + " not found in MongoDB."));
            } else {
                Long sqlId = Long.parseLong(id); // Convert ID to Long for SQL
                Optional<Employee> employee = employeeJpaRepository.findById(sqlId);
                return employee.orElseThrow(() -> new FusionApiException("Employee with ID " + sqlId + " not found in MySQL."));
            }
        } catch (NumberFormatException e) {
            throw new FusionApiException("Invalid ID format for SQL: " + id, e);
        } catch (Exception e) {
            throw new FusionApiException("Error Retrieving Employee: " + e.getMessage(), e);
        }
    }

    // Update Employee
    public Employee updateEmployee(String id, Employee employee, boolean useMongo) {
        try {
            if (useMongo) {
                if (!employeeMongoRepository.existsById(id)) {
                    throw new FusionApiException("Employee with ID " + id + " not found in MongoDB.");
                }
                employee.setMongoId(id);
                return employeeMongoRepository.save(employee);
            } else {
                Long sqlId = Long.parseLong(id); // Convert ID to Long for SQL
                if (!employeeJpaRepository.existsById(sqlId)) {
                    throw new FusionApiException("Employee with ID " + sqlId + " not found in MySQL.");
                }
                employee.setId(sqlId);
                return employeeJpaRepository.save(employee);
            }
        } catch (NumberFormatException e) {
            throw new FusionApiException("Invalid ID format for SQL: " + id, e);
        } catch (Exception e) {
            throw new FusionApiException("Error Updating Employee: " + e.getMessage(), e);
        }
    }

    // Delete Employee
    public void deleteEmployee(String id, boolean useMongo) {
        try {
            if (useMongo) {
                if (!employeeMongoRepository.existsById(id)) {
                    throw new FusionApiException("Employee with ID " + id + " not found in MongoDB.");
                }
                employeeMongoRepository.deleteById(id);
            } else {
                Long sqlId = Long.parseLong(id); // Convert ID to Long for SQL
                if (!employeeJpaRepository.existsById(sqlId)) {
                    throw new FusionApiException("Employee with ID " + sqlId + " not found in MySQL.");
                }
                employeeJpaRepository.deleteById(sqlId);
            }
        } catch (NumberFormatException e) {
            throw new FusionApiException("Invalid ID format for SQL: " + id, e);
        } catch (Exception e) {
            throw new FusionApiException("Error Deleting Employee: " + e.getMessage(), e);
        }
    }
}
