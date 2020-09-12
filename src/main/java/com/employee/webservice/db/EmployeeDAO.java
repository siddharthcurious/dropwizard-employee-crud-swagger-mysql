package com.employee.webservice.db;

import com.employee.webservice.models.Employee;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO extends AbstractDAO<Employee> {

    public EmployeeDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Employee findById(long id){
        return get(id);
    }

    public long createEmployee(Employee employee){
        return persist(employee).getId();
    }

    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployees(){
        return list((Query<Employee>) namedQuery("find_employees"));
    }

    public boolean deleteById(long id){
        Employee employee = get(id);
        currentSession().delete(employee);
        return true;
    }

    public Employee update(long id, Employee employee){
        Employee existingEmployee = get(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        currentSession().save(existingEmployee);
        return existingEmployee;
    }

}
