#!/usr/bin/env lumo
;; -*- clojure -*-

(def challenge (first (.slice (.-argv (js/require "process")) 3)))

(defn string->integer-seq
  "Makes \"1234\" -> '(1 2 3 4)"
  [input-string]
  (map js/parseInt (str input-string)))

(defn conj-if-pair-is-same
  "conjs a to coll, if a = b"
  [coll [a b]]
  (if (= a b)
    (conj coll a)
    coll))

(defn pair-by-length
  "Like partition but on speed.
  (pair-by-length 1 [:a :b :c]) -> '([:a :b] [:b :c] [:c :a])'
  (pair-by-length 2 [:a :b :c]) -> '([:a :c] [:b :a] [:c :b])'"
  [pivot coll]
  (let [coll-length (count coll)
        values-by-index (into {} (map-indexed vector coll))]
    (for [[index c] (map-indexed vector coll)
          :let [i+p (+ index pivot)
                pair-index (if (>= i+p coll-length) (- i+p coll-length) i+p)]]
      [c (get values-by-index pair-index)])))

(defn captcha
  "Solves advent of code capcha"
  [partition-fn input-string]
  (->> input-string
       string->integer-seq
       partition-fn
       (reduce conj-if-pair-is-same [])
       (reduce +)))


(println "CHALLENGE")
(println challenge)
(println "---------")
(println "FIRST:" (captcha (partial pair-by-length 1) challenge))
(println "SECOND:" (captcha (partial pair-by-length (/ (count challenge) 2)) challenge))
