package fusionapi.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import fusionapi.model.Employee;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee, String> {

}
