package com.googlecode.hdbc.dbmigrate.io;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class FileSystemFileProviderTest {
    private static final int MIN_FILE_SIZE = 50;

    @Test
    public final void testTemplateContent() throws IOException {
        int atLeastThisBig = MIN_FILE_SIZE;
        FileSystemFileProvider prov = new FileSystemFileProvider("./templates");
        String content = prov.templateContent("ddl");
        assertNotNull(content);
        assertTrue(content.length() > atLeastThisBig);
        assertTrue(content.endsWith("/"));
    }

    @Test
    public final void testUserName() {
        FileSystemFileProvider provider = new FileSystemFileProvider();
        String home = System.getProperty("user.home");
        String actual = provider.userName(home);
        assertTrue(actual.matches("[a-zA-Z]+"));
    }

    /*@Test
    public final void testWriteDoFileToTempDir() throws IOException {
        FileSystemFileProvider prov = new FileSystemFileProvider("C:\\Temp");
        prov.writeDoFile("test.sql", "db-migrate unit test output");
    }*/

    /*@Test
    public final void testMigrationFileList() {
        FileSystemFileProvider prov = new FileSystemFileProvider("C:\\Temp");
        prov.migrationFileList();
    }*/

}
