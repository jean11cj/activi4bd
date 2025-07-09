package com.sistemavuelo.application;

import com.sistemavuelo.domain.Pasajero;
import java.util.List;

public interface PasajeroRepository {
    void guardar(Pasajero pasajero);
    Pasajero buscarPorId(int id);
    List<Pasajero> listarTodos();
    void actualizar(Pasajero pasajero);
    void eliminar(int id);
}