package service

import model.Department
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repository.DepartmentRepository

@Service
class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository

    public List<Department> departmentList(){

    }
}
