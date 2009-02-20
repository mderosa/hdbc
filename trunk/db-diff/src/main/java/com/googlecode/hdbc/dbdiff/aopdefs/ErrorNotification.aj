package com.googlecode.hdbc.dbdiff.aopdefs;

import java.util.Collection;

import com.googlecode.hdbc.dbdiff.model.Analyze;

public aspect ErrorNotification {

    pointcut diffAnalysisResult() : call (Analyze collections(Collection, Collection));

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

}
