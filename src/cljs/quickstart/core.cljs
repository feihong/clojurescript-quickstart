(ns quickstart.core)

(defn set-text [elem-id text]
  (let [el (js/document.getElementById elem-id)]
    (set! (.-textContent el) text)))

(defn on-click [btn-id func]
  (let [el (js/document.getElementById btn-id)]
    (set! (.-onclick el) func)))

(defn random-hanzi []
  (let [start 0x4e00
        end 0x9fff
        ordinal (-> (- end start)
                    (inc)
                    (rand-int)
                    (+ start))
        hanzi (str (char ordinal))]
    (println "Random hanzi" hanzi)
    hanzi))

(defn set-hanzi []
  (set-text "hanzi" (random-hanzi)))

(def shortnames (-> (.-shortnames js/emojione)
                    (clojure.string/split "|")))

(defn get-emoji [shortname]
  (let [div (js/document.createElement "div")
        html (.shortnameToImage js/emojione shortname)]
    (set! (.-innerHTML div) html)
    (let [img (.-firstChild div)]
      {:text (.-alt img)
       :shortname (.-title img)
       :url (.-src img)})))

(defn random-emoji []
  (let [emoji (-> (rand-nth shortnames) get-emoji)]
    (println "Random emoji" emoji)
    emoji))

(defn set-emoji []
  (let [text (-> (random-emoji) :text)]
    (set-text "emoji" text)))

;; Initialization
(enable-console-print!)

(on-click "hanzi-btn" set-hanzi)
(on-click "emoji-btn" set-emoji)

(set-hanzi)
(set-emoji)

(set! (.-imagePathPNG js/emojione) "https://cdn.jsdelivr.net/emojione/assets/3.1/png/128/")
