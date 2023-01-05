package com.example.openapi.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("mysql")
public class MySqlFlywayConfig {

    private static final String BOOK_SCRIPTS_LOCATION = "scripts/book";

    //TODO :: Set this up for mysql when we have the db setup ready
    @Bean
    public DataSource mySqlDataSource() {
        return null;
    }

    @Bean
    public String[] flywayLocations() {
        return new String[]{BOOK_SCRIPTS_LOCATION};
    }

    @Bean
    public Flyway flyway(final DataSource mySqlDataSource,
                         final String[] flywayLocations) {
        return Flyway.configure()
                .defaultSchema("library")
                .dataSource(mySqlDataSource)
                .locations(flywayLocations)
                .load();
    }
}
