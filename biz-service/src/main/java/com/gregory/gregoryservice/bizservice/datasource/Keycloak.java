package com.gregory.gregoryservice.bizservice.datasource;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.gregory.gregoryservice.bizkeycloakmodel.repository",
    entityManagerFactoryRef = "keycloakEntityManager",
    transactionManagerRef = "keycloakTransactionManager")
public class Keycloak {

  @Bean
  @ConfigurationProperties(prefix = "app.datasource.keycloak")
  public DataSource keycloakDataSource() {

    return DataSourceBuilder.create().build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean keycloakEntityManager() {

    LocalContainerEntityManagerFactoryBean em
        = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(keycloakDataSource());
    em.setPackagesToScan("com.gregory.gregoryservice.bizkeycloakmodel.model");
    em.setJpaVendorAdapter(keycloakHibernateJpaVendorAdapter());
    em.setJpaPropertyMap(Map.of(
        "hibernate.hbm2ddl.auto", "none",
        "hibernate.show_sql", "false"
    ));
    return em;
  }

  @Bean
  public HibernateJpaVendorAdapter keycloakHibernateJpaVendorAdapter() {

    final var hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    hibernateJpaVendorAdapter.setShowSql(true);
    hibernateJpaVendorAdapter.setGenerateDdl(true);
    return hibernateJpaVendorAdapter;
  }

  @Bean
  public JpaTransactionManager keycloakTransactionManager() {

    JpaTransactionManager transactionManager
        = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
        keycloakEntityManager().getObject());
    return transactionManager;
  }
}
