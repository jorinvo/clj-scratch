(ns scratch.system)

(def username (System/getProperty "user.name"))
(def home (System/getProperty "user.home"))
(def pwd (System/getProperty "user.dir"))
(def os {:name (System/getProperty "os.name")
         :version (System/getProperty "os.version")})
