(ns scratch.net
  (:import (java.net URI InetAddress Inet4Address Inet6Address))
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

; Thanks to https://github.com/clojure-cookbook/clojure-cookbook/blob/master/05_network-io/5-03_sending-a-ping-request.asciidoc
(defn ping
  "Time an .isReachable ping to a given domain"
  ([domain]
   (ping domain 3000))
  ([domain timeout]
   (let [addr (java.net.InetAddress/getByName domain)
         start (. System (nanoTime))
         result (.isReachable addr timeout)
         total (/ (double (- (. System (nanoTime)) start)) 1000000.0)]
     {:time total
      :result result})))

(defn nslookup
  "Returns a map of found IPs for a given domain"
  [domain]
  (->> (InetAddress/getAllByName domain)
       (map #(cond (instance? Inet4Address %) {:ip4 [(.getHostAddress %)]}
                   (instance? Inet6Address %) {:ip6 [(.getHostAddress %)]}))
       (apply merge-with #(vec (concat %1 %2)))))
