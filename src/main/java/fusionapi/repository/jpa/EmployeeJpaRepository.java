package fusionapi.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fusionapi.model.Employee;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {

}
