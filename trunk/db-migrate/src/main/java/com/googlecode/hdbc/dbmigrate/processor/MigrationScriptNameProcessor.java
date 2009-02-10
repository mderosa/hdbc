package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.googlecode.hdbc.dbmigrate.MenuItem;

public class MigrationScriptNameProcessor implements IInputProcessor {

    @Override
    public void process(String input, List<MenuItem> items) throws IOException {
        
    } 

    protected String preprocessRawInputName(String input) {
        StringBuilder temp = new StringBuilder();
        String[] parts = input.split("[-_]");
        for (String part : parts) {
            String head = part.substring(0, 1);
            String tail = part.substring(1);
            head = head.toUpperCase();
            temp.append(head + tail);
        }
        return temp.toString();
    }
    
    protected String nextFileIndex(List<String> fileNames) {
        return "";
    }
}
