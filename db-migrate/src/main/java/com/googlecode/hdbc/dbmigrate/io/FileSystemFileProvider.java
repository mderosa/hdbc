package com.googlecode.hdbc.dbmigrate.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public class FileSystemFileProvider implements IFileProvider {
    private static final int BUFFER_LENGTH = 512;
    private static final String DO_DIRECTORY = "do" + File.separator;
    private static final String UNDO_DIRECTORY = "undo" + File.separator;
    private static final String CRLF = "\n";
    private static final String STD_HEADER = CRLF +
            "WHENEVER SQLERROR EXIT SQL.SQLCODE\n" +
            CRLF +
            "VARIABLE    migrate_user    VARCHAR2(64)\n" +
            "BEGIN\n" +
            "    :migrate_user := 'user here';\n" +
            "END;\n" +
            "/" +
            CRLF;
    private static final String SUBSTITUTION_RGX = "user here";
    private String workingDir;

    public FileSystemFileProvider() {
        String defaultDir = ".";
        File working = new File(defaultDir);
        try {
            workingDir = working.getCanonicalPath();
        } catch (IOException e) {
            workingDir = defaultDir;
        }
    }

    public FileSystemFileProvider(final String workingDirectory) {
        this.workingDir = workingDirectory;
    }

    @Override
    public final String[] migrationFileList() {
        File dir = new File(this.workingDir + File.separator + DO_DIRECTORY);
        String[] files = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String fileName) {
                return fileName.endsWith(".sql");
            }
        });
        return files;
    }

    @Override
    public final String templateContent(final String templateType) throws IOException {
        StringBuilder name = new StringBuilder()
            .append(this.workingDir)
            .append(File.separator)
            .append("templates")
            .append(File.separator)
            .append(templateType.toLowerCase())
            .append(".template");
        File file = new File(name.toString());
        FileReader reader = new FileReader(file);
        char[] buffer = new char[BUFFER_LENGTH];
        StringBuilder temp = new StringBuilder();
        while(-1 != reader.read(buffer)) {
            temp.append(buffer);
        }
        return temp.toString().trim();
    }

    @Override
    public final void writeDoFile(final String fileName, final String fileContent) throws IOException {
        this.writeFile(DO_DIRECTORY, fileName, fileContent);
    }

    @Override
    public final void writeUndoFile(final String fileName, final String fileContent) throws IOException {
        this.writeFile(UNDO_DIRECTORY, fileName, fileContent);
    }

    private void writeFile(final String subDirectory, final String fileName, final String fileContent) throws IOException {
        StringBuilder name = new StringBuilder()
        .append(this.workingDir)
        .append(File.separator)
        .append(subDirectory)
        .append(fileName);

        File file = new File(name.toString());
        if (file.createNewFile()) {
            FileWriter writer = new FileWriter(file);
            writer.write(fileContent);
            writer.close();
        } else {
            throw new IOException("couldnt create " + name.toString());
        }
    }

    @Override
    public final void writeMasterMigrationDoScript(final List<String> files) throws IOException {
        String user = userName(System.getProperty("user.home"));
        String header = STD_HEADER.replaceFirst(SUBSTITUTION_RGX, user);
        StringBuilder buffer = new StringBuilder(header);
        for (String file : files) {
            buffer.append("@@ do/")
                .append(file)
                .append(CRLF);
        }
        this.writeFile("", "do_migration.sql", buffer.toString());
    }

    @Override
    public final void writeMasterMigrationUnDoScript(final List<String> files) throws IOException {
        String user = userName(System.getProperty("user.home"));
        String header = STD_HEADER.replaceFirst(SUBSTITUTION_RGX, user);
        StringBuilder buffer = new StringBuilder(header);
        for (int n = files.size(); n > 0; n--) {
            String doFileName = files.get(n - 1);
            String undoFileName = doFileName.replaceFirst("-do_", "-undo_");
            buffer.append("@@ undo/")
                .append(undoFileName)
                .append(CRLF);
        }
        this.writeFile("", "undo_migration.sql", buffer.toString());
    }

    protected final String userName(final String userHome) {
        int pos = userHome.lastIndexOf(File.separator);
        return userHome.substring(pos + 1);
    }
}
