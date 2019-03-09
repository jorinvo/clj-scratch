(ns scratch
  (:require
    [rebel-readline.core]
    [rebel-readline.clojure.main]
    [rebel-readline.clojure.line-reader]
    [rebel-readline.clojure.service.local]
    [clojure.pprint :refer [pprint]]))

(defn -main []
  (rebel-readline.core/with-line-reader
  (rebel-readline.clojure.line-reader/create
    (rebel-readline.clojure.service.local/create))
  (clojure.main/repl
     :prompt (fn []) ;; prompt is handled by line-reader
     :read (rebel-readline.clojure.main/create-repl-read)
     :print pprint)))
