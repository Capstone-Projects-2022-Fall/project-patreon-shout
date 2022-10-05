package com.patreonshout.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * JPA Testing Configuration for in-memory database testing
 */
@Configuration
@EnableScheduling
@EnableJpaRepositories
@Profile("test")
@TestPropertySource("/application-test.properties")
public class TestConfig {

    /**
     * env is used to get environment variables assigned in application.properties
     */
    @Autowired
    private Environment env;

    /**
     * Configures the data source to our database
     *
     * @return a {@link javax.sql.DataSource} object that will connect to our database
     */
    @Bean
    public DataSource dataSource() {
        System.out.println(env.getProperty("spring.datasource.driver-class-name"));
        System.out.println(env.getProperty("spring.datasource.url"));

        DataSourceBuilder<?> dataSource = DataSourceBuilder.create();
        dataSource.driverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.url(env.getProperty("spring.datasource.url"));
        dataSource.username(env.getProperty("spring.datasource.username"));
        dataSource.password(env.getProperty("spring.datasource.password"));
        return dataSource.build();
    }

    /**
     * Java Persistence API Transaction Manager between the program and our database
     *
     * @return a {@link org.springframework.orm.jpa.JpaTransactionManager} object that will manage the
     * transactions we have with our database
     */
    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    /**
     * Connects Hibernate's persistence provider and Hibernate's Session as extended EntityManager interface, and adapts
     * the JpaVendorAdaptor configuration settings
     *
     * @return a {@link org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter} object that lets us use Hibernate with
     * the EntityManager
     */
    private HibernateJpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    /**
     * Configures the {@link javax.persistence.EntityManager} that is used to create database communications in the application
     *
     * @return a {@link org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean} that is used to create our Entity Manager
     */
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        entityManagerFactoryBean.setPackagesToScan();
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());

        return entityManagerFactoryBean;
    }

    /**
     * Configures JPA Hibernate Properties to show the sql used when communicating with the database
     *
     * @return a {@link java.util.Properties} that the Entity Manager will use to be configured
     */
    private Properties jpaHibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.show_sql", true);
        return props;
    }
}
