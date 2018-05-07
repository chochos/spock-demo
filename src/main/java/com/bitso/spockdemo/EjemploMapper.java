package com.bitso.spockdemo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Blabla.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 3:43 PM
 */
public class EjemploMapper implements RowMapper<Ejemplo> {

    @Override
    public Ejemplo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ejemplo e = new Ejemplo();
        e.setClave(rs.getLong("clave"));
        e.setNombre(rs.getString("nombre"));
        e.setCreado(rs.getTimestamp("creado"));
        e.setMonto(rs.getBigDecimal("monto"));
        return e;
    }
}
