(ns scratch
  (:require
    [clojure.repl :refer [apropos dir doc source]]
    [clojure.java.io :as io]
    [clojure.java.shell :refer [sh]]
    [clojure.data :refer [diff]]
    [clojure.edn :as edn]
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
    [scratch.system :refer [username home pwd os]]
    [scratch.java :refer [jmethods]]
    [scratch.json :as json]
    [scratch.net :refer [valid-port? valid-url? get-free-port hostname ping nslookup]]
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
