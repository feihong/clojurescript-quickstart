(set-env!
  :source-paths #{"src/cljs"}
  :resource-paths #{"html"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.9.946"]
                  [adzerk/boot-cljs "2.1.4"]
                  [pandeiro/boot-http "0.8.3"]
                  [org.clojure/tools.nrepl "0.2.13"]
                  [adzerk/boot-reload "0.5.2"]
                  [adzerk/boot-cljs-repl "0.3.3"]
                  [com.cemerick/piggieback "0.2.1"]
                  [weasel "0.7.0"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])

(deftask dev
  "Launch immediate feedback development environment"
  []
  (comp
    (serve :dir "target" :port 8000)
    (watch)
    (reload)
    (cljs-repl)
    (cljs)
    (target :dir #{"target"})))
