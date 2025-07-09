package com.sistemavuelo.infrastructure.input;

import com.sistemavuelo.domain.Pasajero;
import com.sistemavuelo.infrastructure.output.postgres.JdbcPasajeroRepository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/jdbc.properties"));

        Connection conn = DriverManager.getConnection(
            props.getProperty("jdbc.url"),
            props.getProperty("jdbc.user"),
            props.getProperty("jdbc.password")
        );

        JdbcPasajeroRepository repo = new JdbcPasajeroRepository(conn);
        repo.guardar(new Pasajero(1020, "pedro pica paez"));

        List<Pasajero> pasajeros = repo.listarTodos();
        pasajeros.forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
    }
}