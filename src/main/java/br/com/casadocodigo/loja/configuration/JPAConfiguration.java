package br.com.casadocodigo.loja.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();		
		JpaVendorAdapter vendoAdapter = new HibernateJpaVendorAdapter();
		
		factoryBean.setJpaVendorAdapter(vendoAdapter);		
		factoryBean.setDataSource(dataSource);		
		factoryBean.setJpaProperties(getAdditionalJPAProperties());		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryBean;
	}
	
	@Bean
	@Profile("Dev")
	private DataSource createDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:sqlite:C:\\Users\\mcosta\\Murilo\\dev\\murilo\\casadocodigo.db");
		dataSource.setDriverClassName("org.sqlite.JDBC");
		
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
	    return new JpaTransactionManager(factory);
	}
	
	private Properties getAdditionalJPAProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect" ,     "com.enigmabridge.hibernate.dialect.SQLiteDialect");
		properties.setProperty("hibernate.show_sql",     "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		return properties;
	}
	
	@SuppressWarnings("unused")
	private void createMysqlDataSource(LocalContainerEntityManagerFactoryBean factoryBean) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("casadocodigo");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:mysql://mysql-database:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		factoryBean.setDataSource(dataSource);
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect" ,     "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql",     "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(properties);
	}
}
