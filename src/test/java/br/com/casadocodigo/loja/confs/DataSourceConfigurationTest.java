package br.com.casadocodigo.loja.confs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import br.com.casadocodigo.loja.configuration.JPAConfiguration;

public class DataSourceConfigurationTest {
	
	@Bean
    @Profile("Test")
    public DataSource createDataSource(){
		DriverManagerDataSource dataSource = (DriverManagerDataSource) new JPAConfiguration().createMysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_test");
		
        return dataSource;
    }
}
