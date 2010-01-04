
(ns com.ociweb.main
  (:gen-class)
  (:import (java.io File))
  (:use com.ociweb.file)
  (:use com.ociweb.compressor)
  (:use clojure.contrib.duck-streams))

(defn output-proc-summary [w s]
  (.write w (:proc-name s))
  (.println w)
  (.write w (:content s))
  (.println w))

(defn output-file-summaries [w d]
  (loop [fs (directory-source-files d)]
    (when-let [f (first fs)]
      (.write w (str "class:" (.getName f)))
      (.println w)
      (doseq [ln (content-summary f)] (.write w (str "    " ln "\n")))
      (recur (rest fs)))))

(defn output-dir-summaries [w a]
  (loop [ds (descendant-directories (list (File. a)))]
    (when-let [d (first ds)]
      (if (not (empty? (directory-source-files d)))
	(do
	  (.write w (.getCanonicalPath d))
	  (.println w)
	  (output-file-summaries w d)))
      (recur (rest ds)))))

(defn -main [a & as]
  (if (nil? a)
    (println "a starting search directory is required")
    (with-open [w (writer "./sumerian.log")]
      (output-dir-summaries w a))))

