(ns scratch
  (:require
    [rebel-readline.core]
    [rebel-readline.clojure.main]
    [rebel-readline.clojure.line-reader]
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
    [org.httpkit.client :as http]
    [org.httpkit.server :refer [run-server]]
    [org.httpkit.timer :as timer]
    [org.httpkit.encode :refer [base64-encode]]
    [scratch.system :refer :all]
    [scratch.java :refer :all]
    [scratch.json :as json]
    [scratch.http :refer :all]
    [scratch.fs :refer :all]   [rebel-readline.clojure.service.local]))

(defn -main []
  (in-ns 'scratch)
  (rebel-readline.core/with-line-reader
    (rebel-readline.clojure.line-reader/create
      (rebel-readline.clojure.service.local/create))
    (clojure.main/repl
      :prompt (fn []) ;; prompt is handled by line-reader
      :read (rebel-readline.clojure.main/create-repl-read)
      :print pprint)))
