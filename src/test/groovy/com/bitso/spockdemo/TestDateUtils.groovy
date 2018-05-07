package com.bitso.spockdemo

import groovy.transform.TypeChecked
import spock.lang.Specification

import java.text.SimpleDateFormat
import java.util.stream.Collectors

/** Blabla.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 4:33 PM
 */
class TestDateUtils extends Specification {

    void uno(Map<String, Integer> x) {
        println "${x.a} y ${x.b}"
    }

    void "quita la hora"() {
        given: "Un formatter"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        expect: "Debe salir sin hora"
        sdf.format(DateUtils.removeHourOfDay(new Date(fecha))) == formateada + " CACA"
        where: "Distintos dias"
        fecha          | formateada
        1525728973789L | "2018-05-07 00:00:00"
        1525297020525L | "2018-05-02 00:00:00"
    }
}
