package com.WindSkull.SchoolWebApp;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.holonplatform.core.datastore.Datastore;
import com.holonplatform.jpa.spring.EnableJpaDatastore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
@EnableJpaDatastore
@Configuration
public class ConfigurationClass {

	


	@Bean
	public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource) {
	      LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	      emf.setDataSource(dataSource);
	      emf.setPackagesToScan("com.WindSkull.SchoolWebApp.models.entities");
	      return emf;
	  }

	
	@Bean
	public DataSource dataSource() 
	{
		return DataSourceBuilder .create()
		.username("root") .password("") .url("jdbc:mysql://localhost:3306/test").build(); 
	}
	

	@Autowired
	Datastore datastore;

}
