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
       
        List<Pasajero> pasajeros = repo.listarTodos();
        System.out.println("=== Buscar pasajero por ID ===");
        Pasajero encontrado = repo.buscarPorId(1); // Aquí puedes cambiar el ID
            if (encontrado != null) {
            System.out.println("ID: " + encontrado.getId());
            System.out.println("Nombre: " + encontrado.getNombre());
            } else {
            System.out.println("Pasajero no encontrado");
                }
        pasajeros.forEach(p -> System.out.println(p.getId() + ": " + p.getNombre()));
    }
}
