package com.example.openapi.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class H2FlywayConfig {

    private static final String BOOK_SCRIPTS_LOCATION = "scripts/book";

    @Bean
    public String[] flywayLocations() {
        return new String[]{BOOK_SCRIPTS_LOCATION};
    }

    @Bean
    public Flyway flyway(final DataSource dataSource,
                         final String[] flywayLocations) {
        return Flyway.configure()
                .defaultSchema("library")
                .dataSource(dataSource)
                .locations(flywayLocations)
                .load();
    }
}
