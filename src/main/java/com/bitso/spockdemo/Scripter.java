package com.bitso.spockdemo;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Blabla.
 *
 * @author Enrique Zamudio
 * Date: 5/7/18 4:51 PM
 */
public class Scripter {

    public static String loadScript(String path) {
        File dir;
        dir = new File("src/main/sql").getAbsoluteFile();
        if (dir.exists() && dir.isDirectory()) {
            dir = new File(dir, path);
            if (dir.exists() && dir.isFile() && dir.canRead()) {
                try (FileReader reader = new FileReader(dir)) {
                    char[] buf = new char[16384];
                    int read = reader.read(buf);
                    StringBuilder sb = new StringBuilder();
                    while (read > 0) {
                        sb.append(buf, 0, read);
                        read = reader.read(buf);
                    }
                    return sb.toString();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return null;
        } else {
            throw new IllegalStateException("Couldn't find proper dir! " + dir);
        }
    }

    /** Runs a "SELECT * FROM [testTableName]" and if it fails, runs the
     * creation script at path.
     * @return true if the script was executed. */
    public static boolean create(String creationScriptPath, String testTableName, JdbcTemplate jdbc) {
        try {
            jdbc.queryForObject("SELECT count(*) FROM " + testTableName, Long.class);
            return false;
        } catch (DataAccessException ex) {
            jdbc.execute(loadScript(creationScriptPath));
            return true;
        }
    }
}
