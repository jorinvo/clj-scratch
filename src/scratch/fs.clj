(ns scratch.fs
  (:require
    [clojure.java.io :as io]
    [scratch.system :refer [pwd]]))

(defn ls
  ([]
   (ls pwd))
  ([f]
   (map #(hash-map :name (.getName %) :dir (.isDirectory %))
        (.listFiles (io/file f)))))

(defn dir?
  "Check if f is a directory"
  [f]
  (.isDirectory (io/file f)))

(defn exists?
  "Check if f exists"
  [f]
  (.exists (io/file f)))
