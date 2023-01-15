package com.example.openapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class H2FlywayConfig {

    private static final String BOOK_SCRIPTS_LOCATION = "classpath:scripts/book";

/*    @Bean
    public String[] flywayLocations() {
        return new String[]{BOOK_SCRIPTS_LOCATION};
    }

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.url}") final String jdbcUrl,
                                 @Value("${spring.datasource.username}") final String username,
                                 @Value("${spring.datasource.password}") final String password,
                                 @Value("${spring.datasource.driverClassName}") final String driverClassName) {

        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        return new HikariDataSource(hikariConfig);
    }
    @Bean
    public Flyway flyway(final DataSource dataSource,
                         final String[] flywayLocations) {
        System.out.println("-----------Flyway Bean Loaded------------");
        return Flyway.configure()
                .defaultSchema("library")
                .dataSource(dataSource)
                .locations(flywayLocations)
                .baselineOnMigrate(true)
                .load();
    }*/
}
