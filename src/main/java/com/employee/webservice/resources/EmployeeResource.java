package com.employee.webservice.resources;

import com.codahale.metrics.annotation.Timed;
import com.employee.webservice.db.EmployeeDAO;
import com.employee.webservice.models.Employee;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/employees")
@Api("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    private final EmployeeDAO employeeDAO;

    public EmployeeResource(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Timed
    @ApiOperation("A Employee")
    public Employee findEmployee(@PathParam("id") long id){
        return this.employeeDAO.findById(id);
    }

    @GET
    @UnitOfWork
    @Timed
    @ApiOperation("All employees")
    public List<Employee> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }

    @POST
    @UnitOfWork
    @Timed
    @ApiOperation("Create an employee")
    public long createEmployee(@Valid @NotNull Employee employee){
        return employeeDAO.createEmployee(employee);
    }

    @PUT
    @UnitOfWork
    @Timed
    @Path("/{id}")
    @ApiOperation("Update an employee")
    public Employee updateEmployee(@PathParam("id") long id, @Valid @NotNull Employee employee){
        return employeeDAO.update(id, employee);
    }

    @DELETE
    @UnitOfWork
    @Timed
    @Path("/{id}")
    @ApiOperation("Delete and employee")
    public boolean delete(@PathParam("id") long id){
        return employeeDAO.deleteById(id);
    }

}
