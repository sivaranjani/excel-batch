package com.cgi.excel.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManagerFactory",
		 transactionManagerRef = "excelTransactionManager",
		basePackages = { "com.cgi.excel.repo" })
public class DBConfiguration {

	private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = { "com.cgi.excel.entity" };
	@Autowired
	private Environment env;
	
	@Bean(name="dataSource")
	public DataSource dataSource() {

		String username = env.getProperty("spring.datasource.username");
		String password = env.getProperty("spring.datasource.password");
		String driverClass = env.getProperty("spring.datasource.driver-class-name");
		String url = env.getProperty("spring.datasource.url");

		return DataSourceBuilder.create().username(username).password(password).url(url).driverClassName(driverClass)
				.build();
	}
	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {

		return builder.dataSource(dataSource).packages(ENTITYMANAGER_PACKAGES_TO_SCAN).build();
	}
	
	@Bean(name="excelTransactionManager")
	public JpaTransactionManager jpaTransactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory factory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(factory);
		return transactionManager;
	}
}