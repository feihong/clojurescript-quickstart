(ns quickstart.core
  (:require [quickstart.hanzi]
            [quickstart.emoji]))

(defn set-text [elem-id text]
  (let [el (js/document.getElementById elem-id)]
    (set! (.-textContent el) text)))

(defn on-click [btn-id func]
  (let [el (js/document.getElementById btn-id)]
    (set! (.-onclick el) func)))


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

; (on-click "emoji-btn" set-emoji)
; (set-emoji)

(quickstart.hanzi/render)
(quickstart.emoji/render)
