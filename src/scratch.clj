(ns scratch
  (:require
    [clojure.repl :refer [apropos dir doc source]]
    [clojure.reflect :refer [reflect]]
    [clojure.java.io :as io]
    [clojure.java.shell :refer [sh]]
    [clojure.data :refer [diff]]
    [clojure.java.browse :refer [browse-url]]
    [clojure.pprint :refer [pp pprint print-table]]
    [clojure.set :as set]
    [clojure.spec.alpha :as spec]
    [clojure.string :as str]
    [clojure.walk :refer [postwalk]]
    [rebel-readline.core]
    [rebel-readline.clojure.main]
    [rebel-readline.clojure.line-reader]
    [rebel-readline.clojure.service.local]
    [cider-nrepl.main :as cider]
    [aleph.http :as http]
    [aleph.tcp :as tcp]
    [aleph.udp :as udp]
    [byte-streams :as byte-streams]
    [manifold.stream :as stream]
    [manifold.deferred :as deferred]
    [java-time :as time]
    [clojure.java.jdbc :as jdbc]
    [buddy.core.hash :as hash]
    [buddy.core.mac :as mac]
    [buddy.core.codecs :as codecs]
    [buddy.core.codecs.base64 :as base64]
    [buddy.hashers :as hashers]
    [pl.danieljanus.tagsoup :as tagsoup]
    [hiccup.core :refer [html]]
    [postal.core :as postal]
    [scratch.system :refer [username home pwd os]]
    [scratch.java :refer [jmethods]]
    [scratch.json :as json]
    [scratch.net :refer [valid-port? valid-url? hostname ping nslookup]]
    [scratch.fs :refer [ls dir? exists?]]))

(defn -main []
  (in-ns 'scratch)
  (doto (Thread. #(cider/init)) (.setDaemon true) .start)
  (rebel-readline.core/with-line-reader
    (rebel-readline.clojure.line-reader/create
      (rebel-readline.clojure.service.local/create))
    (clojure.main/repl
      :prompt (fn []) ;; prompt is handled by line-reader
      :read (rebel-readline.clojure.main/create-repl-read)
      :print pprint)))
