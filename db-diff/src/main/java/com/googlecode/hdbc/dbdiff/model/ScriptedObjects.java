package com.googlecode.hdbc.dbdiff.model;

import java.util.ArrayList;

public class ScriptedObjects extends DbObjects<String> {

    public ScriptedObjects(final String dbName) {
        super(dbName, new ArrayList<String>());
    }

    @Override
    public final boolean equals(final Object obj) {
        if (obj == null || !(obj instanceof ScriptedObjects)) {
            return false;
        }
        boolean equals = true;
        ScriptedObjects temp = (ScriptedObjects) obj;
        ArrayList<String> myLines = (ArrayList<String>) this.getConstituentObjects();
        ArrayList<String> othersLines = (ArrayList<String>) temp.getConstituentObjects();
        if (myLines.size() == othersLines.size()) {
            for (int i = 0; i < myLines.size(); i++) {
                if (!myLines.get(i).equals(othersLines.get(i))) {
                    equals = false;
                }
            }
        } else {
            equals = false;
        }
        return equals;
    }

}
