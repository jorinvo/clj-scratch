(ns scratch.http
  (:import (java.net URI))
  (:require
   [clojure.string :as str]
   [org.httpkit.client :refer [url-encode]]))

(defn valid-port? [p]
  (< 0 p 0x10000))

(defn valid-url? [s]
  (try
    (.toURL (java.net.URI. s))
    true
    (catch Exception e false)))

(defn query-string
  "Returns URL-encoded query string for given params map."
  [m]
  (->> m
       (map (fn [[k v]]  (str (url-encode (name k)) "=" (url-encode v))))
       (str/join "&")))

(defn hostname [s]
  (.getHost (new URI s)))
