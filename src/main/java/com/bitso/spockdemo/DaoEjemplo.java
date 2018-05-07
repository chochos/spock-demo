package com.bitso.spockdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Blabla.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 3:37 PM
 */
@Component
@RequiredArgsConstructor
public class DaoEjemplo {

    private final JdbcTemplate jdbc;

    public Optional<Ejemplo> get(long id) {
        return Optional.empty();
    }

    public void update(Ejemplo e) {
        jdbc.update("UPDATE ejemplo SET nombre=?, monto=? WHERE clave=?",
                e.getNombre(), e.getMonto(), e.getClave());
    }

    public void insert(Ejemplo e) {
    }

    public void delete(Ejemplo e) {
        jdbc.update("DELETE FROM ejemplo WHERE clave=?", e.getClave());
    }

    public List<Ejemplo> fetchByNombre(String nombre) {
        return jdbc.query("SELECT * FROM ejemplo WHERE nombre ILIKE ?", new EjemploMapper(),
                "*" + nombre + "*");
    }

    public List<Ejemplo> fetchCreatedBefore(Date when) {
        return jdbc.query("SELECT * FROM ejemplo WHERE creado < ?", new EjemploMapper(),
                when);
    }

}
