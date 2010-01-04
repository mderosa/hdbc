
(ns com.ociweb.compressor
  (:gen-class)
  (:use clojure.contrib.duck-streams)
  (:import (java.io File)))

(defn procedure-name [ln]
  (second(re-find #"(\w{1,})\s*\(" ln)))

(defn is-public-procedure? [ln]
  (.find
   (re-matcher #"\s*public(\s+static)?(\s+final)?\s+[\w, <>]{1,}\s+\w{1,}\s*\(" ln)))

(defn compress-line [ln]
  (let [matches (re-seq #"[;{}]" ln)]
    (reduce str matches)))

(defn- char-count [s char]
  (reduce (fn [v a] (if (= char a) (inc v) v)) 0 s))

(defn compressed-proc-terminated? [cln]
  (let [left-bracket-count (char-count cln \{)
	right-bracket-count (char-count cln \})]
    (if (< left-bracket-count right-bracket-count)
      (throw (IllegalStateException. "we have read past the end of a procedure")))
    (if (not (.contains cln "{"))
      false
      (if (> left-bracket-count right-bracket-count)
	false
	(if (= left-bracket-count right-bracket-count)
	  true)))))

(defn compress-lines [ls acc]
  (if (or (empty? ls) (compressed-proc-terminated? acc))
    acc
    (compress-lines (rest ls) (str acc (compress-line (first ls))))))


(defn lines2summarys [lines]
  (loop [result '() ls lines]
    (if (empty? ls)
      result
      (if (is-public-procedure? (first ls))
	(recur (cons (procedure-name (first ls)) (cons (compress-lines ls "") result)) (rest ls))
	(recur result (rest ls))))))

(defn content-summary [#^File f]
  (let [lines (read-lines f)]
    (lines2summarys lines)))
