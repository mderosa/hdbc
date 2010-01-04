
(ns com.ociweb.file_test
  (:use clojure.test
	com.ociweb.file))

(deftest test-java-source-file?
  (is (= true (java-source-file? "/home/odyssues/test.java")))
  (is (= false (java-source-file? "/home/odysseus/teest.jav"))))

(deftest test-hidden-dir
  (is (= true (hidden-dir? "/home/odysseus/.git")))
  (is (= true (hidden-dir? "/home/odysseus/.")))
  (is (= true (hidden-dir? "/home/odyssues/..")))
  (is (= false (hidden-dir? "/home/odysseus/temp"))))
