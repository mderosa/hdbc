
CLOJURE_LIB=lib/clojure.jar:lib/clojure-contrib.jar
ANT_LIB=lib/ant.jar:lib/ant-launcher.jar
java -cp $CLOJURE_LIB:$ANT_LIB:src:test:classes clojure.main -r