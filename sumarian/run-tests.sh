
CLOJURE_LIB=lib/clojure.jar:lib/clojure-contrib.jar
java -cp $CLOJURE_LIB:src:test:classes clojure.main test/com/ociweb/all_tests.clj