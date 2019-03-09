(ns user
  (:require
    [clojure.repl :refer [apropos dir doc source]]
    [clojure.java.io :as io]
    [clojure.java.shell :refer [sh]]
    [clojure.data :refer [diff]]
    [clojure.java.browse :refer [browse-url]]
    [clojure.pprint :refer [pp pprint print-table]]
    [clojure.set :as set]
    [clojure.spec.alpha :as spec]
    [clojure.string :as str]))

