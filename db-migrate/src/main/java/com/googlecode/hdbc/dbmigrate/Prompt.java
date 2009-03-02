package com.googlecode.hdbc.dbmigrate;

public enum Prompt {
    ONE_TWO_THREE {
        @Override
        public final String toString() {
            return "('1','2', or '3'):";
        }
    },

    ALPHANUMERIC {
        @Override
        public final String toString() {
            return "(alphanumeric string):";
        }
    },

    DDL_DML {
        @Override
        public final String toString() {
            return "('ddl' or 'dml'):";
        }
    },

    NUMERIC {
        @Override
        public final String toString() {
            return "(numeric):";
        }
    }
}
