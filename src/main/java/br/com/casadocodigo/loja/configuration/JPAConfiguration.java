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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties additionalJPAProperties) {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();		
		JpaVendorAdapter vendoAdapter = new HibernateJpaVendorAdapter();
		
		factoryBean.setJpaVendorAdapter(vendoAdapter);		
		factoryBean.setDataSource(dataSource);		
		factoryBean.setJpaProperties(additionalJPAProperties);		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryBean;
	}
	
	@Bean
	@Profile("Dev")
	public DataSource createDataSource() {
		return createSqliteDataSource();
	}
	
	@Bean
	@Profile("Dev")
	public Properties additionalJPAProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",      getSqliteJPADialect());
		properties.setProperty("hibernate.show_sql",     "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		
		return properties;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
	    return new JpaTransactionManager(factory);
	}
	
	public DataSource createMysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("casadocodigo");
		dataSource.setPassword("casadocodigo");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		return dataSource;
	}
	
	public DataSource createSqliteDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:sqlite:C:\\Users\\mcosta\\Murilo\\dev\\murilo\\casadocodigo.db");
		dataSource.setDriverClassName("org.sqlite.JDBC");
		
		return dataSource;
	}
	
	private String getMysqlJPADialect() {
		return "org.hibernate.dialect.MySQL5Dialect";
	}
	
	private String getSqliteJPADialect() {
		return "com.enigmabridge.hibernate.dialect.SQLiteDialect";
	}
}
