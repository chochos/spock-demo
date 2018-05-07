package com.bitso.spockdemo;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpockDemoApplication {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("sa");
        ds.setPassword("sa");
        ds.setUrl("jdbc:h2:mem:bitso;MODE=PostgreSQL");
        ds.setDriverClassName("org.h2.Driver");
        JdbcTemplate jdbc = new JdbcTemplate(ds);
        jdbc.setDatabaseProductName("PostgreSQL");
        return new JdbcTemplate(ds);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpockDemoApplication.class, args);
	}
}
