package com.bsuir.spring.service.employee;

import com.bsuir.spring.model.Employee;
import com.bsuir.spring.model.EmployeeRole;
import com.bsuir.spring.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(int id);

    void deleteEmployeeById(int id);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
