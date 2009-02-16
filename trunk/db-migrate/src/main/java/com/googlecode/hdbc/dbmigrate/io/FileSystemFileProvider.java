package com.googlecode.hdbc.dbmigrate.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

public class FileSystemFileProvider implements IFileProvider {
    private static final int BUFFER_LENGTH = 512;
    private static final String USER_HOME = "user.home";
    private static final String DO_DIRECTORY = "do";
    private static final String UNDO_DIRECTORY = "undo";
    private static final String TEMPLATES_DIRECTORY = "templates";
    private static final String SCRIPT_PARAMETER = " :migrate_user";
    private static final String SUCCESS = "!!! Success !!!";
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

    public final String[] migrationFileList() {
        String directory = this.workingDir + File.separator + DO_DIRECTORY;
        return this.getSqlFileNamesInDirectory(directory);
    }

    private String[] getSqlFileNamesInDirectory(final String directory) {
        File dir = new File(directory);
        String[] files = dir.list(new FilenameFilter() {

            public boolean accept(final File file, final String fileName) {
                return fileName.endsWith(".sql");
            }
        });
        return files;
    }

    public final String templateContent(final String templateType) throws IOException {
        StringBuilder name = new StringBuilder()
            .append(this.workingDir)
            .append(File.separator)
            .append(TEMPLATES_DIRECTORY)
            .append(File.separator)
            .append(templateType.toLowerCase())
            .append(".template");
        return fileContent(name.toString());
    }

    private String fileContent(final String filePath) throws IOException {
        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        char[] buffer = new char[BUFFER_LENGTH];
        StringBuilder temp = new StringBuilder();
        while(-1 != reader.read(buffer)) {
            temp.append(buffer);
            buffer = new char[BUFFER_LENGTH];
        }
        return temp.toString().trim();
    }

    public final void writeDoFile(final String fileName, final String fileContent) throws IOException {
        this.writeFile(DO_DIRECTORY, fileName, fileContent);
    }

    public final void writeUndoFile(final String fileName, final String fileContent) throws IOException {
        this.writeFile(UNDO_DIRECTORY, fileName, fileContent);
    }

    private void writeFile(final String subDirectory, final String fileName, final String fileContent) throws IOException {
        StringBuilder name = new StringBuilder()
        	.append(this.workingDir);
        if (!"".equals(subDirectory)) {
            name.append(File.separator).append(subDirectory);
        }
        name.append(File.separator).append(fileName);

        File file = new File(name.toString());
        if (file.exists()) {
        	file.delete();
        }
        if (file.createNewFile()) {
            FileWriter writer = new FileWriter(file);
            writer.write(fileContent);
            writer.close();
        } else {
            throw new IOException("couldnt create " + name.toString());
        }
    }

    public final void writeMasterMigrationDoScript(final List<String> files) throws IOException {
        String user = userName(System.getProperty(USER_HOME));
        String header = STD_HEADER.replaceFirst(SUBSTITUTION_RGX, user);
        StringBuilder buffer = new StringBuilder(header);
        for (String file : files) {
            buffer.append("prompt running ")
            	.append(file)
            	.append(CRLF)
            	.append("@@ do/")
                .append(file)
                .append(SCRIPT_PARAMETER)
                .append(CRLF);
        }
        buffer.append(SUCCESS);
        this.writeFile("", "do_migration.sql", buffer.toString());
    }

    public final void writeMasterMigrationUnDoScript(final List<String> files) throws IOException {
        String user = userName(System.getProperty(USER_HOME));
        String header = STD_HEADER.replaceFirst(SUBSTITUTION_RGX, user);
        StringBuilder buffer = new StringBuilder(header);
        for (int n = files.size(); n > 0; n--) {
            String doFileName = files.get(n - 1);
            String undoFileName = doFileName.replaceFirst("-do_", "-undo_");
            buffer.append("prompt running ")
            	.append(undoFileName)
            	.append(CRLF)
            	.append("@@ undo/")
                .append(undoFileName)
                .append(SCRIPT_PARAMETER)
                .append(CRLF);
        }
        buffer.append(SUCCESS);
        this.writeFile("", "undo_migration.sql", buffer.toString());
    }

    protected final String userName(final String userHome) {
        int pos = userHome.lastIndexOf(File.separator);
        return userHome.substring(pos + 1);
    }

    public final void initializeDoDirectory(final String schema) throws IOException {
        initializeMigrationDirectory(schema, DO_DIRECTORY);
    }

    public final void initializeUnDoDirectory(final String schema) throws IOException {
        initializeMigrationDirectory(schema, UNDO_DIRECTORY);
    }

    private void initializeMigrationDirectory(final String schema, final String migrationDir) throws IOException {
        StringBuilder initDir = new StringBuilder().append(workingDir)
            .append(File.separator)
            .append(migrationDir);
        makeDirectory(initDir.toString());

        StringBuilder templateDir = new StringBuilder()
            .append(this.workingDir)
            .append(File.separator)
            .append(TEMPLATES_DIRECTORY)
            .append(File.separator)
            .append(migrationDir);
        String[] templateFileNames = getSqlFileNamesInDirectory(templateDir.toString());
        for (String templateFileName : templateFileNames) {
            String content = fileContent(templateDir.toString() +
                    File.separator + templateFileName);
            String substituted = content.replaceAll("\\$\\{schema\\}", schema);
            this.writeFile(migrationDir, templateFileName, substituted);
        }
    }

    private void makeDirectory(final String directory) throws IOException {
        File dir = new File(directory);
        boolean created = dir.mkdir();
        if (!created) {
            throw new IOException("unable to create directory, " + directory);
        }
    }
}
