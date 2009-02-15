package com.googlecode.hdbc.dbmigrate.io;

import java.io.IOException;
import java.util.List;

public interface IFileProvider {

    String[] migrationFileList();
    String templateContent(String templateType) throws IOException;
    void writeDoFile(String fileName, String fileContent) throws IOException;
    void writeUndoFile(String fileName, String fileContent) throws IOException;
    void writeMasterMigrationDoScript(List<String> files) throws IOException;
    void writeMasterMigrationUnDoScript(List<String> files) throws IOException;

    void initializeDoDirectory(String schema) throws IOException;
    void initializeUnDoDirectory(String schema) throws IOException;
}
