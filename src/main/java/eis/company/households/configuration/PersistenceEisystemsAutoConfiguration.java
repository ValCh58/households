package eis.company.households.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(basePackages = "eis.company.households.repoeisystems", 
                       entityManagerFactoryRef = "eisystemsEntityManager", 
                       transactionManagerRef = "eisystemsTransactionManager")

public class PersistenceEisystemsAutoConfiguration {

	@Autowired
	private Environment env;

	public PersistenceEisystemsAutoConfiguration(Environment env) {
		super();
		this.env = env;
	}


	@Bean
	@ConfigurationProperties(prefix = "spring.eisystems-datasource")
	public DataSource eisystemsDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean eisystemsEntityManager() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(eisystemsDataSource());
		em.setPackagesToScan("eis.company.households.modeleis");
		em.setPersistenceUnitName("eisystemsEntityManager");
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.ddl.auto", env.getProperty("hibernate.ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		em.setJpaPropertyMap(properties);

		return em;
	}
	
	@Bean
	public PlatformTransactionManager eisystemsTransactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(eisystemsEntityManager().getObject());
		return transactionManager;
	}
}
