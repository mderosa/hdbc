
(ns com.ociweb.file
  (:gen-class)
  (:import (java.io File)))

(defn java-source-file? [path]
  (.endsWith path ".java"))

(defn hidden-dir? [path]
  (let [elmnts (seq (.split path (File/separator)))]
    (.startsWith (last elmnts) ".")))

(defn children-directories [#^File d]
  (if (nil? d)
    (list)
    (let [dirs1 (filter #(.isDirectory %) (seq (.listFiles d)))]
      (filter #(not (hidden-dir? (.getCanonicalPath %))) dirs1))))

(defn descendant-directories [[d & ds]]
  (let [children (children-directories d)]
    (cond
     (nil? d) (list)
     (and (not (nil? d)) (nil? ds)) (cons d (descendant-directories children))
     true (cons d (descendant-directories (into ds children))))))

(defn directory-source-files [#^File d]
  (filter #(java-source-file? (.getName %)) (seq (.listFiles d))))