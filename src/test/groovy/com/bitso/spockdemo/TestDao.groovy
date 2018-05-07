package com.bitso.spockdemo

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import spock.lang.Shared
import spock.lang.Specification

import javax.annotation.Resource

/** Blabla.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 4:53 PM
 */
@SpringBootTest
class TestDao extends Specification {

    @Shared
    boolean ya
    @Resource
    JdbcTemplate jdbc
    @Resource
    DaoEjemplo dao

    void setup() {
        if (!ya) {
            Scripter.create('tablas.sql', 'ejemplo', jdbc)
            jdbc.update("INSERT INTO ejemplo VALUES (1, 'chochos', DATEADD(DAY, -5, TODAY), 5.0)")
            jdbc.update("INSERT INTO ejemplo VALUES (2, 'serch', DATEADD(DAY, 1, TODAY), 3.0)")
            ya = true
        }
    }

    void insert() {

    }

    void update() {

    }

    void delete() {
        given:
        jdbc.update("INSERT INTO ejemplo VALUES (3, 'bastian', DATEADD(DAY, 15, TODAY), 3.0)")
        when:
        dao.delete(new Ejemplo(clave:3))
        then:
        jdbc.queryForList("SELECT * FROM ejemplo WHERE clave=3").empty
        cleanup:
        jdbc.update("DELETE FROM ejemplo WHERE clave=3")
    }

    void "created before"() {
        given:
        jdbc.update("INSERT INTO ejemplo VALUES (3, 'bastian', DATEADD(DAY, 15, TODAY), 3.0)")
        when:
        List<Ejemplo> ex = dao.fetchCreatedBefore(new Date())
        then:
        ex.size() == 1
        ex[0].nombre == 'chochos'
        ex[0].clave == 1

        when: "con fecha exacta"
        ex = dao.fetchCreatedBefore(ex[0].creado)
        then:
        ex.empty

        when:
        ex = dao.fetchCreatedBefore(new Date()+10)
        then: "me encuentra dos"
        ex.size() == 2
        and: "no esta el pinche bastian"
        !(3 in ex.collect { it.clave })
        ex.findAll { it.clave in [1L,2L] }
        cleanup:
        jdbc.update("DELETE FROM ejemplo WHERE clave=3")
    }

}
