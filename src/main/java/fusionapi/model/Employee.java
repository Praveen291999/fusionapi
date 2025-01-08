package fusionapi.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employees") // MongoDB Collection Mapping
@Entity // JPA Entity Mapping
@Table(name = "employee") // SQL Table Mapping for JPA
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

	@jakarta.persistence.Id // MongoDB Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // SQL Auto-Generated ID
	private Long id;

	@Transient // Exclude this field from SQL mapping
	@org.springframework.data.annotation.Id
	private String mongoId;

	@Column(name = "first_name", nullable = false) // For Jpa ->cannot insert null in db
	@Field("first_name") // For MongoDB
	private String firstName;

	@Column(name = "last_name", nullable = false)
	@Field("last_name")
	private String lastName;

	@Column(name = "department", nullable = false)
	@Field("department")
	private String department;

	@Column(name = "age", nullable = false)
	@Field("age")
	private int age;

	@Column(name = "country", nullable = false)
	@Field("country")
	private String country;

	@Column(name = "state", nullable = false)
	@Field("state")
	private String state;

	@Column(name = "city", nullable = false)
	@Field("city")
	private String city;

	@Column(name = "salary", nullable = false)
	@Field("salary")
	private double salary;

}