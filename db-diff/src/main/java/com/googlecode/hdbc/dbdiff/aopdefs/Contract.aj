package com.googlecode.hdbc.dbdiff.aopdefs;

import com.googlecode.hdbc.dbdiff.DbDiff;

import java.util.Properties;

public aspect Contract {

    pointcut dbDiffReadPropertiesPostCond() : call (Properties DbDiff.readProperties());

    Properties around(DbDiff t) : dbDiffReadPropertiesPostCond()
        && target(t){
        Properties props = proceed(t);
        if (props.getProperty(DbDiff.TEST_CONNECTION_STRING) == null ||
                props.getProperty(DbDiff.TEST_USER) == null ||
                props.getProperty(DbDiff.TEST_PWD) == null ||
                props.getProperty(DbDiff.REF_CONNECTION_STRING) == null ||
                props.getProperty(DbDiff.REF_USER) == null ||
                props.getProperty(DbDiff.REF_PWD) == null) {
                throw new IllegalStateException("the configuration file is missing configuration entries");
            }
        return props;
    }

}
