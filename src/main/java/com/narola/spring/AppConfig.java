package com.narola.spring;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.narola.spring")
@EnableWebMvc
@PropertySource("classpath:db-config.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.narola.spring.repository")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

//    @Bean
//    public EntityManagerFactory entityManager() {
//        return Persistence.createEntityManagerFactory("persistenceUnit");
//    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        Map<String, Object> jpaPropertiesMap = new HashMap<>();
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_DRIVER, env.getProperty("db.driver"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_USER, env.getProperty("db.user"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_PASSWORD, env.getProperty("db.password"));
        jpaPropertiesMap.put(AvailableSettings.JPA_JDBC_URL, env.getProperty("db.url"));
//        jpaPropertiesMap.put(AvailableSettings.HBM2DDL_DATABASE_ACTION, env.getProperty(AvailableSettings.HBM2DDL_DATABASE_ACTION));
        jpaPropertiesMap.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty(AvailableSettings.HBM2DDL_AUTO));
        jpaPropertiesMap.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        jpaPropertiesMap.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));

        LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localEntityManagerFactoryBean.setPersistenceUnitName("persistenceUnit");
        localEntityManagerFactoryBean.setPackagesToScan("com.narola.spring");
        localEntityManagerFactoryBean.setJpaPropertyMap(jpaPropertiesMap);
        return localEntityManagerFactoryBean;
    }

    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("message");
        return messageSource;
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver irv = new InternalResourceViewResolver();
        irv.setPrefix("/WEB-INF/pages/");
        irv.setSuffix(".jsp");
        return irv;
    }

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
        return multipartResolver;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setHttpClient(httpClient);
        return clientHttpRequestFactory;
    }
}
