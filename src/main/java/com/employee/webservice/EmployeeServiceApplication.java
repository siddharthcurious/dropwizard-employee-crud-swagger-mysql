package com.employee.webservice;

import com.employee.webservice.db.EmployeeDAO;
import com.employee.webservice.models.Employee;
import com.employee.webservice.resources.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class EmployeeServiceApplication extends Application<EmployeeServiceConfiguration> {

    @Override
    public String getName() {
        return "EmployeeService";
    }

    public static void main(final String[] args) throws Exception {
        new EmployeeServiceApplication().run(args);
    }

    private final SwaggerBundle<EmployeeServiceConfiguration> swagger = new SwaggerBundle<EmployeeServiceConfiguration>(){
        @Override
        public SwaggerBundleConfiguration getSwaggerBundleConfiguration(EmployeeServiceConfiguration configuration) {
            return configuration.getSwaggerBundleConfiguration();
        }
    };

    private final HibernateBundle<EmployeeServiceConfiguration> hibernate = new HibernateBundle<EmployeeServiceConfiguration>(Employee.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(EmployeeServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<EmployeeServiceConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(swagger);
    }

    @Override
    public void run(final EmployeeServiceConfiguration configuration, final Environment environment) {
        final EmployeeDAO dao = new EmployeeDAO(hibernate.getSessionFactory());
        environment.jersey().register(new EmployeeResource(dao));
    }

}
