(ns scratch.json
  (:refer-clojure :exclude [str read])
  (:require
   [clojure.java.io :as io]
   [jsonista.core :as json]))

(defn str
  "Return JSON string of data"
  [data]
  (json/write-value-as-string data))

(defn strp
  "Return pretty JSON string of data"
  [data]
  (json/write-value-as-string data (json/object-mapper {:pretty true})))

(defn write
  "Write data as JSON to file"
  [file data]
  (json/write-value (io/file file) data (json/object-mapper {:pretty true})))

(defn read
  "Read data as JSON from string or file"
  [file]
  (json/read-value file (json/object-mapper {:decode-key-fn true})))

