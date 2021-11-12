package com.example.springjdbcapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;
import java.util.stream.*;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SpringJdbcApiApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SpringJdbcApiApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcApiApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE Personnes");
        jdbcTemplate.execute("CREATE TABLE Personnes(" +
                "id INTEGER, prenom VARCHAR(255), nom VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Stream.of("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting Personnes record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO Personnes(prenom, nom) VALUES (?,?)", splitUpNames);

        log.info("Querying for Personnes records where prenom = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, prenom, nom FROM Personnes WHERE prenom = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new Personnes(rs.getInt("id"), rs.getString("prenom"), rs.getString("nom"))
        ).forEach(Personnes -> log.info(Personnes.toString()));
    }
}
