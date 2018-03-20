package br.com.casadocodigo.loja.confs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {
	
	@Bean
    @Profile("Test")
    public DataSource createDataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:sqlite:C:\\Users\\mcosta\\Murilo\\dev\\murilo\\casadocodigo_test.db");
		dataSource.setDriverClassName("org.sqlite.JDBC");
        return dataSource;
    }
}
