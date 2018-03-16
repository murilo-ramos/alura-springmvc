package br.com.casadocodigo.loja.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendoAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendoAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:sqlite:C:\\Users\\mcosta\\Murilo\\dev\\murilo\\casadocodigo.db");
		dataSource.setDriverClassName("org.sqlite.JDBC");
		factoryBean.setDataSource(dataSource);
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect" ,     "com.enigmabridge.hibernate.dialect.SQLiteDialect");
		properties.setProperty("hibernate.show_sql",     "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(properties);
		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
	    return new JpaTransactionManager(factory);
	}

}
