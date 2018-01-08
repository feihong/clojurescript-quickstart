(ns quickstart.emoji
  (:require [reagent.core :as r]))


(set! (.-imagePathPNG js/emojione) "https://cdn.jsdelivr.net/emojione/assets/3.1/png/128/")
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


(defn handle-generate [state]
  (let [old-emoji (:current @state)
        new-emoji (random-emoji)
        new-history (conj (:history @state) (:text old-emoji))]
    (swap! state assoc :current new-emoji :history new-history)))


(defn history [state]
  [:div "History: "
    [:span {:class "emoji-history"}
           (clojure.string/join " " (:history @state))]])



(defn component []
  (let [state (r/atom {:current (random-emoji)
                       :history []})]
    (fn []
      [:div "Random Emoji: "
       [:span {:class "emoji"} (-> @state :current :text)]
       [:div
        [:button {:class "btn btn-primary"
                  :on-click #(handle-generate state)}
                 "Generate"]
        (history state)]])))


(defn render []
  (r/render [component] (js/document.getElementById "emoji-component")))
