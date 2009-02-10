package com.googlecode.hdbc.dbmigrate.io;

import java.util.List;

public interface IFileProvider {

    List<String> migrationFileList();
}
