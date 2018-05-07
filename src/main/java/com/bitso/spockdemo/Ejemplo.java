package com.bitso.spockdemo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Blabla.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 3:19 PM
 */
@Data
public class Ejemplo {
    private long clave;
    private String nombre;
    private Date creado;
    private BigDecimal monto;
}
