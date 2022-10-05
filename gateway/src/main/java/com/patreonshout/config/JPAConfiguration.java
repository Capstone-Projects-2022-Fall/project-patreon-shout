package com.patreonshout.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * SpringBoot Database Management Configuration
 */
@Configuration
public class JPAConfiguration {

    /**
     * dsUrl is the data source url that is used to connect to the database
     */
    @Value("${spring.datasource.url}") String dsUrl;
    /**
     * dsUsername is the data source username we use to connect to the database
     */
    @Value("${spring.datasource.username}") String dsUsername;
    /**
     * dsPassword is the data source password we use to connect to the database
     */
    @Value("${spring.datasource.password}") String dsPassword;

    /**
     * Configures the data source to our database
     *
     * @return a {@link javax.sql.DataSource} object that will connect to our database
     */
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSource = DataSourceBuilder.create();
        dataSource.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.url(dsUrl);
        dataSource.username(dsUsername);
        dataSource.password(dsPassword);
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
