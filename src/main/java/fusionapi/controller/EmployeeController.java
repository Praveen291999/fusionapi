package fusionapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fusionapi.Exception.FusionApiException;
import fusionapi.model.Employee;
import fusionapi.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Create or Update Employee
	@PostMapping
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee, @RequestParam boolean useMongo) {
		try {
			Employee savedEmployee = employeeService.saveEmployee(employee, useMongo);
			return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		} catch (FusionApiException e) {
			return createErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 Bad Request
		} catch (Exception e) {
			return createErrorResponse("An unexpected error occurred: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
		}
	}

	// Get Employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable String id, @RequestParam boolean useMongo) {
		try {
			Employee employee = employeeService.getEmployeeById(id, useMongo);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (FusionApiException e) {
			return createErrorResponse("Employee not found with ID: " + id, HttpStatus.NOT_FOUND); // 404 Not Found
		} catch (Exception e) {
			return createErrorResponse("An unexpected error occurred: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
		}
	}

	// Update Employee
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable String id, @RequestBody Employee employee,
			@RequestParam boolean useMongo) {
		try {
			Employee updatedEmployee = employeeService.updateEmployee(id, employee, useMongo);
			return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
		} catch (FusionApiException e) {
			return createErrorResponse("Employee not found with ID: " + id, HttpStatus.NOT_FOUND); // 404 Not Found
		} catch (Exception e) {
			return createErrorResponse("An unexpected error occurred: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
		}
	}

	// Delete Employee
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable String id, @RequestParam boolean useMongo) {
		try {
			employeeService.deleteEmployee(id, useMongo);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
		} catch (FusionApiException e) {
			return createErrorResponse("Employee not found with ID: " + id, HttpStatus.NOT_FOUND); // 404 Not Found
		} catch (Exception e) {
			return createErrorResponse("An unexpected error occurred: " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
		}
	}

	// Helper method to generate error responses
	private ResponseEntity<Map<String, String>> createErrorResponse(String message, HttpStatus status) {
		return new ResponseEntity<>(Map.of("error", message), status);
	}
}