(ns quickstart.hanzi
  (:require [reagent.core :as r]))


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


(def state (r/atom {:current (random-hanzi)
                    :history []}))


(defn handle-generate []
  (let [old-hanzi (:current @state)
        new-hanzi (random-hanzi)
        new-history (conj (:history @state) old-hanzi)]
    (swap! state assoc :current new-hanzi :history new-history)))


(defn component []
  [:div "Random Hanzi: "
   [:span {:class "hanzi"} (:current @state)]
   [:div
    [:button {:class "btn btn-primary"
              :on-click handle-generate}
             "Generate"]]
   [:div "History: "
     [:span (clojure.string/join " " (:history @state))]]])


(defn render []
  (r/render [component] (js/document.getElementById "hanzi-component")))
