(ns quickstart.hanzi
  (:require [reagent.core :as reagent]))


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


(defn component []
  (let [hanzi (reagent/atom (random-hanzi))]
    [:div "Random Hanzi: "
     [:span {:class "hanzi"} @hanzi]
     [:div
      [:button {:class "btn btn-primary"
                :on-click #(reset! hanzi (random-hanzi))} "Generate"]]]))


(defn render []
  (reagent/render [component] (js/document.getElementById "hanzi-component")))
