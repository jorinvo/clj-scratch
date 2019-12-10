# Scratch

*Clojure REPL with features loaded ready to be productive*

Being able to explore a problem with programming tools in an interactive fashion can be very productive.

There have been countless times I had to do some one-off investigating
and a wrote a quick shell one-liner that grew into a monster of ten pipes
with five sub-shells, three `xargs` and quoted quotes within quoted quotes.

Clojure is an amazing tool for interactive tasks, but just starting up a plain REPL,
it's hard to be productive right away for one-off tasks.
You need to load some additional libraries depending on your context.

`scratch` is an opinionated toolbox to have everything ready
whenever I realize my shell commands are getting out of hand again.

Of course loading all these dependencies comes at a cost,
however not being interrupted while working on a problem can be a good trait-off,
especially if you keep the same REPL session running for most of the time anyways.
The most common tools are required by default and the other dependencies can be loaded on demand.

## Run It

Try it out with:

```sh
clojure -Sdeps '{:deps {clj-scratch {:git/url "https://github.com/jorinvo/clj-scratch" :sha "c7dfa3e6f496e30d5ff39506fb893c5fc733081c"}}}' -m scratch
```

A Git hash can be used to simply try out the REPL without downloading anything manually,
but I prefer using a local copy of the repository so I can easily adopt changes as I go.

I created an alias like this:

```sh
alias scratch='clojure -Sdeps "{:deps {clj-scratch {:local/root \"/path/to/clj-scratch\"}}}" -m scratch'
```


## Features

### Dev Tools

- Use [rebel-readline](https://github.com/bhauman/rebel-readline) with all its nice tools and shortcuts
- Pretty printed REPL output
- [CIDER nREPL](https://github.com/clojure-emacs/cider-nrepl) server to connect from your editor
- View docs with [`(doc map)`](https://clojuredocs.org/clojure.repl/doc)
- View source with [`(source map)`](https://clojuredocs.org/clojure.repl/source)
- List public vars with [`(dir clojure.string)`](https://clojuredocs.org/clojure.repl/dir)
- Search vars with [`(apropos str)`](https://clojuredocs.org/clojure.repl/apropos)
- Quick info about Java methods of an object with `(jmethods "string")`
- Table output with `(print-table (jmethods 0))`

### OS

- Info About Operating System in vars `username`, `home`, `pwd`, `os`
- Run shell commands using `(sh "cowsay" "hi")`

### File System

- Directory listing with `(ls)` or `(ls "some/path")`
- Check file properties with helpers like `(dir? "some/path")` and `(exists? "some/path")`
- Also `clojure.java.io` is available as `io`. Checkout `(jmethods (io/file "."))`

### JSON

- Using [jsonista](https://github.com/metosin/jsonista)
- Read JSON from file or streams with `(json/read (io/file "some.json"))`
- Output data as JSON with `(json/str data)`, `(json/strp data)` or `(json/write "path/out.json" data)`

### Networking

- Check network types with helpers like `(valid-port? 112233)` or `(valid-url? "not-valid")`
- Get host name from URL with `(hostname "https://example.com")`
- Get a random free port with `(get-free-port)`
- Ping a domain with `(ping "example.com")`
- Get IPs for a domain with `(nslookup "example.com")`

#### [Aleph](https://github.com/ztellman/aleph)

Aleph provides great tooling to work with HTTP, WebSockets, TCP and UDP as client and server.

It's included as dependency but not loaded by default:

```clojure
(require '[aleph.http :as http]
         '[aleph.tcp :as tcp]
         '[aleph.udp :as udp]
         '[byte-streams :as byte-streams]
         '[manifold.stream :as stream]
         '[manifold.deferred :as deferred])
```

Example - Make a HTTP requests:

```clojure
(-> @(http/get "https://example.com") :body byte-streams/to-string)
```

Checkout [Aleph](https://github.com/ztellman/aleph) for more.

### HTML

- [hiccup](https://github.com/weavejester/hiccup) to generate HTML
- Parse HTML with [tagsoup](https://github.com/nathell/clj-tagsoup)
- Use [specter](https://github.com/nathanmarz/specter) to transform deeply nested structures (like HTML!)

The tools are included as dependencies but not loaded by default:

```clojure
(require '[hiccup.core :refer [html]])
(require '[com.rpl.specter :as specter])
(require '[pl.danieljanus.tagsoup :as tagsoup])

(def data (tagsoup/parse "https://example.com"))

(def CHILDREN (specter/nthpath 2))

(defn where-tag [tag]
  [(specter/pred #(= tag (first %))) CHILDREN])

(def TITLE [CHILDREN
            (where-tag :head)
            (where-tag :title)])

(html (specter/transform TITLE str/upper-case data))
```

### SQL

- [`jdbc`](https://github.com/clojure/java.jdbc) is available
- Make sure to require it first:

```clojure
(require '[clojure.java.jdbc :as jdbc])
```

- SQLite, PostgreSQL and MySQL drivers are included

### Working with time

- Using [java-time](https://github.com/dm3/clojure.java-time)

```clojure
(require '[java-time :as time])
```

- There are excellent examples [here](https://github.com/dm3/clojure.java-time#an-appetizer)

### Cryptography

- [buddy](https://github.com/funcool/buddy-core) is included to provide crypto functionality

```clojure
(require '[buddy.core.hash :as hash]
         '[buddy.core.mac :as mac]
         '[buddy.core.codecs :as codecs]
         '[buddy.core.codecs.base64 :as base64]
         '[buddy.hashers :as hashers])
```

- Common namespaces are available: `hash`, `mac`, `codecs`, `base64`` hashers`
- sha hash: `(-> (hash/sha256 "some val") (codecs/bytes->hex))`
- base64 string: `(codecs/bytes->str (base64/encode "some val"))`

### More core data structures

Commonly used core namespaces are already available:

```clojure
[clojure.edn :as edn]
[clojure.data :refer [diff]]
[clojure.set :as set]
[clojure.string :as str]
[clojure.walk :refer [postwalk]]
[clojure.spec.alpha :as spec]
```


## License

[MIT](https://github.com/jorinvo/clj-scratch/blob/master/LICENSE)
