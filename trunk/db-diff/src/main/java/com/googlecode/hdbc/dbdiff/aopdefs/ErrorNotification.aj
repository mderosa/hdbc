package com.googlecode.hdbc.dbdiff.aopdefs;

import java.util.Collection;
import com.googlecode.hdbc.dbdiff.DbDiff;
import com.googlecode.hdbc.dbdiff.db.IDatabase;
import com.googlecode.hdbc.dbdiff.model.Analyze;

public aspect ErrorNotification {

    pointcut diffAnalysisResult() : call (Analyze collections(Collection, Collection));
    pointcut diffProgressReport(String s) :
        ((call (boolean DbDiff.diffTabularObject(IDatabase, IDatabase, String))) ||
        (call (boolean DbDiff.diffScriptedObject(IDatabase, IDatabase, String)))) && args(*,*,s);

    after(Analyze an) returning : diffAnalysisResult()
        && target(an) {
        int totalUniqueElementsInTestDb = an.getLeftOnly().size();
        int totalUniqueElementsInRefDb = an.getRightOnly().size();
        if (totalUniqueElementsInTestDb + totalUniqueElementsInRefDb > 0) {
            System.out.println("Differences Found in: " + an.getAnalysisTarget());
            for (Object obj : an.getLeftOnly()) {
                System.out.println("unique to test db: " + obj.toString());
            }
            for (Object obj : an.getRightOnly()) {
                System.out.println("unique to ref db: " + obj.toString());
            }
            System.out.println("");
        }

    }

    before(String objName) : diffProgressReport(objName) {
        System.out.println("examining " + objName);
    }

}
